package org.cs.dp.sonar.service.impl;

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
import org.cs.dp.radar.api.entity.RestPartyMru;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.CourseHistoryMapper;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
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
    private CourseHistoryMapper courseHistory;
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

    public ReturnInfo<CourseGetByIdResBean> getById(Integer id) {
        CourseGetByIdResBean courseGetByIdRes = courseMapper.selectById(id);
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_GETCONFINFO, String.valueOf(courseGetByIdRes.getYsx_id()));
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestOngoingConf restOngoingConf = (RestOngoingConf) returnInfo.getReturnData();
            List<RestParty> restPartyMrus = restOngoingConf.getPartyMrus().get(0).getParties();
            //持续时长
            Integer duration = restOngoingConf.getBasicInfo().getDuration();
            Map<String, RestParty> map = restPartyMrus.stream().collect(Collectors.toMap(RestParty::getDeviceId, a -> a, (k1, k2) -> k1));

            List<CourseResBean> courseDevices = courseDeviceMapper.selectByCourseId(id);
            List<CourseResBean> courseDeviceList = new ArrayList<>();
            if (courseDevices.size() > 0) {
                CourseResBean courseRes = courseDevices.stream().filter(
                        e -> e.getIsMain().equals(1)).collect(Collectors.toList()).get(0);
                courseDevices.remove(courseRes);
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
                RestParty restParty = map.get(String.valueOf(courseRes.getYsx_id()));
                if (null != restParty) {
                    courseRes.setMute(restParty.isAudioMuted());
                    courseRes.setStatus("connected".equals(restParty.getConnectionStatus()) ? 1 : 2);
                    courseRes.setSideLines(restParty.isMonitor());
                }
                courseGetByIdRes.setDuration(DateUtil.secToTime(duration));
                courseGetByIdRes.setMain(courseRes);
            }
        }
        return new ReturnInfo(courseGetByIdRes);
    }

    /**
     * 1.讲日程信息添加到日程表
     * 2.将设备添加到关联的设备表
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo startSchedule(Integer id, Long ysx_id) {
        ScheduleArrayBean res = scheduleMapper.selectById(id);
        if (null == res) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "选择日程不能在!");
        }

        String currentDateStr = DateUtil.getCurrentDate(DateUtil.YMDHMS);
        CourseEntity course = new CourseEntity(
                null, res.getName(), res.getType(), res.getState(), res.getTeacher_name(),
                res.getIsRecord(), res.getIsLive(), null, null, null, null,
                null, null, null, currentDateStr, null, currentDateStr, null, null,
                null, null, res.getResolving_power(), null, null, null, null, 1000,
                null, res.getDevice_id(), res.getDevice_ids(), null, null,
                null, null, null, null, null, null,
                res.getBandwidth(), res.getOrg_id(), ysx_id);
        courseMapper.insertSelective(course);

        //端控制
        courseDeviceMapper.insertByScheduleId(course.getCourse_id(), id);

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
        courseHistory.insertSelectCurrent(param.getCourse_id());
        courseMapper.deleteByPrimaryKey(param.getCourse_id());
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