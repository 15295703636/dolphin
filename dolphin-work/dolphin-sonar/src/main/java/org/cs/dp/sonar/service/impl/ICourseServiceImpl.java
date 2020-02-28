package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.radar.api.entity.RestOngoingConf;
import org.cs.dp.radar.api.entity.RestParty;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;
import org.cs.dp.sonar.mapper.*;
import org.cs.dp.sonar.service.ICourseService;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ICourseServiceImpl
 * @Description 进行中日程管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 11:21:47
 **/
@Service
public class ICourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private CourseHistoryMapper courseHistoryMapper;
    @Autowired
    private CourseHistoryDeviceMapper courseHistoryDeviceMapper;
    @Autowired
    private ISoMruService iSoMruService;

    @Override
    public ReturnInfo delCourse(Integer param) {
        courseMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCourse(CourseEntity param) {
        courseMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    /**
     * 查询进行中日程列表
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo getCourse(RequestPage<SplitPageInfo, GetCourseReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());

        UserConditionBean userConditionBean = new UserConditionBean();
        GetCourseReqBean getCourseReqBean = param.getInfo();
        getCourseReqBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        if (null != getCourseReqBean.getOrg_id() && 0 != getCourseReqBean.getOrg_id()) {
            userConditionBean.setOrgId(getCourseReqBean.getOrg_id());
        }
        List<CourseEntity> resList = courseMapper.selectByCondition(getCourseReqBean);
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    /**
     * 查询日程ID查询教学列表数据
     *
     * @param id
     * @return
     */
    public ReturnInfo<CourseGetByIdResBean> getById(Integer id) {
        //查询信息
        CourseGetByIdResBean courseGetByIdRes = courseMapper.selectById(id);
        //查询会议信息
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_GETCONFINFO, String.valueOf(courseGetByIdRes.getYsx_id()));
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestOngoingConf restOngoingConf = (RestOngoingConf) returnInfo.getReturnData();
            //获取会议设备状态信息
            List<RestParty> restPartyMrus = restOngoingConf.getPartyMrus().get(0).getParties();
            Map<String, RestParty> map = restPartyMrus.stream().collect(Collectors.toMap(RestParty::getDeviceId, a -> a, (k1, k2) -> k1));
            //持续时长
            Integer duration = restOngoingConf.getBasicInfo().getDuration();
            courseGetByIdRes.setDuration(DateUtil.secToTime(duration));

            List<CourseResBean> courseDevices = courseDeviceMapper.selectByCourseId(id);
            List<CourseResBean> courseDeviceList = new ArrayList<>();
            if (courseDevices.size() > 0) {
                //筛选处理主讲端 静音/连接/旁观 信息
                CourseResBean courseRes = courseDevices.stream().filter(
                        e -> e.getIsMain().equals(1)).collect(Collectors.toList()).get(0);
                RestParty mainDevice = map.get(String.valueOf(courseRes.getYsx_id()));
                if (null != mainDevice) {
                    courseRes.setMute(mainDevice.isAudioMuted());
                    courseRes.setStatus("connected".equals(mainDevice.getConnectionStatus()) ? 1 : 2);
                    courseRes.setSideLines(mainDevice.isMonitor());
                }

                //筛选处理远程端 静音/连接/旁观 信息
                courseDevices.forEach(e -> {
                    if (!e.getDevice_id().equals(courseRes.getDevice_id())) {
                        RestParty restParty = map.get(String.valueOf(e.getYsx_id()));
                        if (null != restParty) {
                            e.setMute(restParty.isAudioMuted());
                            e.setStatus("connected".equals(restParty.getConnectionStatus()) ? 1 : 2);
                            e.setSideLines(restParty.isMonitor());
                        }
                        courseDeviceList.add(e);
                    }
                });
                courseGetByIdRes.setRemote(courseDeviceList);
                courseGetByIdRes.setMain(courseRes);
            }
        }
        return new ReturnInfo(courseGetByIdRes);
    }

    /**
     * 1.讲日程信息添加到日程表
     * 2.将设备添加到关联的设备表
     *
     * @param param
     * @param ysx_id
     * @return
     */
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo startCourser(ScheduleStartReqBean param, Long ysx_id) {
        ScheduleArrayBean res = null;
        //历史开会标志 1历史 其他值日程配置开会
        if (1 == param.getHistorySign()) {
            res = courseHistoryMapper.selectById(param.getId());
        } else {
            res = scheduleMapper.selectById(param.getId());
        }
        if (null == res) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "选择日程不能在!");
        }

        CourseEntity course = new CourseEntity();
        course.setCourse_name(res.getName());
        course.setCourse_type(res.getType());
        course.setTeacher_name(res.getTeacher_name());
        course.setDuration(res.getDuration());
        course.setIsLive(res.getIsLive());
        course.setIsRecord(res.getIsRecord());
        course.setBandwidth(res.getBandwidth());
        course.setCreaterId(ThreadLocalUserInfoUtil.get().getUser_id());
        course.setOrg_id(res.getOrg_id());
        course.setYsx_id(ysx_id);
        String currentDateStr = DateUtil.getCurrentDate(DateUtil.YMDHMS);
        course.setStart_time(currentDateStr);
        course.setLocal_classroomId(res.getDevice_id());
        course.setUser_number(res.getUser_number());
        //日程类型未互动，模式状态为上课
        if (2 == res.getType()) {
            course.setClass_state(1);
        }
        //处理会议主题信息
        courseMapper.insertSelective(course);
        //处理端
        if (1 == param.getHistorySign()) {
            courseDeviceMapper.insertByHistoryId(course.getCourse_id(), param.getId());
        } else {
            courseDeviceMapper.insertByScheduleId(course.getCourse_id(), param.getId());
        }
        return new ReturnInfo();
    }


    @Override
    public ReturnInfo liveBroadcast(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo share(Integer id) {
        return new ReturnInfo();
    }

    /**
     * 1.将此条数据插入到历史表，并删除该条信息
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo end(CourseEndReqBean param) throws BaseException {
        Object ysx_res[] = dealEnd(param.getYsx_id());
        if (!(boolean) ysx_res[0]) {
            throw new BaseException("云视讯通讯异常", String.valueOf(ysx_res[1]));
        }
        CourseHistoryEntity courseHistory = JSONObject.parseObject(JSON.toJSONString(courseMapper.selectByIdResAll(param.getCourse_id())), CourseHistoryEntity.class);
        courseHistory.setCourse_id(null);
        courseHistoryMapper.insertSelective(courseHistory);
        courseHistoryDeviceMapper.insertByCourseId(courseHistory.getCourse_id(), param.getCourse_id());

        courseMapper.deleteByPrimaryKey(param.getCourse_id());
        courseDeviceMapper.deleteByCourseId(param.getCourse_id());
        return new ReturnInfo();
    }

    /**
     * 结束会议
     *
     * @param ysx_id
     * @return
     */
    private Object[] dealEnd(Long ysx_id) {
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_STOP, String.valueOf(ysx_id));
        Object obj[] = new Object[2];
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            obj[0] = true;
        } else {
            RestError restError = (RestError) returnInfo.getReturnData();
            int errorCode = restError.getErrorCode();
            if (1407 <= errorCode && errorCode <= 1409) {
                obj[0] = true;
            } else {
                obj[0] = false;
                obj[1] = GetNameByCode.getNameByCode(errorCode);
            }
        }
        return obj;
    }
}