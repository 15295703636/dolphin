package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.RestConf;
import org.cs.dp.radar.api.entity.RestConfReq;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.radar.api.entity.RestSubtitle;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.cs.dp.sonar.config.ThreadAsyncConfig;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.*;
import org.cs.dp.sonar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * @ClassName IScheduleServiceImpl
 * @Description 日程管理实现类
 * @Author LiuJT
 * @Date 2019-12-09 05:55:10
 **/
@Slf4j
@Service
public class IScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private CustomerServerMapper customerServerMapper;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private CourseHistoryMapper courseHistoryMapper;
    @Autowired
    private ScheduleDeviceMapper scheduleDeviceMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private ISoMruService iSoMruService;
    @Autowired
    private ThreadAsyncConfig threadAsyncConfig;
    @Autowired
    private ISoBrsService iSoBrsService;
    @Autowired
    private ICustomerServerService iCustomerServerService;
    @Autowired
    private ICourseDeviceService iCourseDeviceService;
    @Autowired
    private ISoBaioService iSoBaioService;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addSchedule(ScheduleArrayBean param) {

        if (null == param.getDevice_id() && null == param.getUser_id()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "请选择设备!");
        }

        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        param.setCreate_user_id(ThreadLocalUserInfoUtil.get().getUser_id());
        scheduleMapper.insertSelective(param);

        //处理端/软终端数据
        dealDevice(param, true);

        if (param.isNew_start()) {
            start(new ScheduleStartReqBean(param.getId(), 0));
        }
        return new ReturnInfo();
    }

    /**
     * 处理端/软终端数据
     *
     * @param param
     * @param isEdit
     */
    private void dealDevice(ScheduleArrayBean param, boolean isEdit) {
        if (!param.getType().equals(3)) {
            scheduleDeviceMapper.deleteByScheduleId(param.getId());
            if (isEdit) {
                //处理端/软终端数据
                List<ScheduleDeviceEntity> scheduleDevices = new ArrayList<>();
                if (null != param.getDevice_id()) {
                    scheduleDevices.add(new ScheduleDeviceEntity(param.getDevice_id(), param.getId(), 0, 1));
                } else {
                    scheduleDevices.add(new ScheduleDeviceEntity(param.getUser_id(), param.getId(), 1, 1));
                }
                if (null != param.getDeviceIds()) {
                    param.getDeviceIds().forEach(e -> {
                        if (null == param.getDevice_id() || !e.equals(param.getDevice_id())) {
                            scheduleDevices.add(new ScheduleDeviceEntity(e, param.getId(), 0, 2));
                        }
                    });
                }
                if (null != param.getUserIds()) {
                    param.getUserIds().forEach(e -> {
                        if (null == param.getUser_id() || !e.equals(param.getUser_id())) {
                            scheduleDevices.add(new ScheduleDeviceEntity(e, param.getId(), 1, 2));
                        }
                    });
                }
                scheduleDeviceMapper.insertBatch(scheduleDevices);
            }
        }
    }

    @Override
    public ReturnInfo delSchedule(Integer param) {
        ScheduleArrayBean scheduleArray = new ScheduleArrayBean();
        scheduleArray.setId(param);
        dealDevice(scheduleArray, false);
        scheduleMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo editSchedule(ScheduleArrayBean param) {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        scheduleMapper.updateByPrimaryKeySelective(param);
        dealDevice(param, true);
        if (param.isNew_start()) {
            start(new ScheduleStartReqBean(param.getId(), 0));
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param) {

        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<ScheduleArrayBean> resList = (List<ScheduleArrayBean>) scheduleMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());

        resList.forEach(e -> {
            if (null != e.getDevice_ids()) {
                e.setDeviceIds(JSONArray.parseArray("[" + e.getDevice_ids() + "]", Integer.class));
            }
            if (null != e.getUser_ids()) {
                e.setUserIds(JSONArray.parseArray("[" + e.getUser_ids() + "]", Integer.class));
            }
            if (null != e.getDevice_names()) {
                e.setDeviceNames(JSONArray.parseArray("[\"" + e.getDevice_names().replace(",", "\",\"") + "\"]", String.class));
            }
            if (null != e.getUser_names()) {
                e.setUserNames(JSONArray.parseArray("[\"" + e.getUser_names().replace(",", "\",\"") + "\"]", String.class));
            }
            String duration[] = e.getDuration().split(",");
            e.setDuration_hour(Integer.valueOf(duration[0]));
            e.setDuration_minute(Integer.valueOf(duration[1]));

        });
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo getById(Integer id) {
        ScheduleOneDeviceBean res = scheduleMapper.selectById(id);
        if (!res.getType().equals(3)) {
            if (null != res.getDevice_ids()) {
                res.setDeviceIds(JSONArray.parseArray("[" + res.getDevice_ids() + "]", Integer.class));
                res.setDevices(
                        deviceMapper.selectByPrimaryKey(res.getDeviceIds())
                );
            }
            if (null != res.getUser_ids()) {
                res.setUserIds(JSONArray.parseArray("[" + res.getUser_ids() + "]", Integer.class));
                res.setUsers(
                        deviceMapper.selectUserById(res.getUserIds())
                );
            }
            if (null != res.getDevice_names()) {
                res.setDeviceNames(JSONArray.parseArray("[\"" + res.getDevice_names().replace(",", "\",\"") + "\"]", String.class));
            }
            if (null != res.getUser_names()) {
                res.setUserNames(JSONArray.parseArray("[\"" + res.getUser_names().replace(",", "\",\"") + "\"]", String.class));
            }

        }

        String duration[] = res.getDuration().split(",");
        res.setDuration_hour(Integer.valueOf(duration[0]));
        res.setDuration_minute(Integer.valueOf(duration[1]));

        return new ReturnInfo(res);
    }

    /**
     * 开启日程会议
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo start(ScheduleStartReqBean param) {
        //调用会捷通接口开启会议    处理结果 会议id 流媒体id 流媒体服务地址
        Object ysxRes[] = dealStart(param);
        log.info("第一步：调用会捷通接口开启会议结果：{}", JSONArray.toJSONString(ysxRes));

        //判断日程会议是否成功
        if (!(boolean) ysxRes[0]) {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, (String) ysxRes[1]);
        }
        //保存当前日程信息  操作结果； 课程类型； 是否开启直播
        Object resData[] = iCourseService.startCourser(param, (Long) ysxRes[1], ysxRes);
        log.info("第二步：保存当前日程信息结果：{}", JSONArray.toJSONString(resData));

        ReturnInfo returnInfo = new ReturnInfo();

        //开启是否开启直播处理
        if ((boolean) resData[0]) {
            //开启直播 || 日程类型为录播课
            if ("1".equals(resData[2]) || "3".equals(resData[1])) {
                returnInfo = iCourseDeviceService.startLiveStreaming(String.valueOf(ysxRes[1]), String.valueOf(resData[1]));
                log.info("第三步：开启是否开启直播处理结果：{}", JSON.toJSONString(returnInfo));
            }
        }
        return returnInfo;
    }

    /**
     * @param param
     * @return 处理结果 会议id 流媒体id 流媒体服务地址
     */
    private Object[] dealStart(ScheduleStartReqBean param) {
        ScheduleOneDeviceBean res = null;
        if (1 == param.getHistorySign()) {
            res = courseHistoryMapper.selectByIdResYsx(param.getId());
        } else {
            res = scheduleMapper.getStartInfoById(param.getId());
        }

        Object obj[] = new Object[4];
        //非录播课调用会捷通
        if (!res.getType().equals(3)) {
            RestConfReq restConfReq = new RestConfReq();
            List<Long> userIds = new ArrayList<>();
            List<Long> deviceIds = new ArrayList<>();
            if (null != res.getDevice_ids()) {
                deviceIds = JSONArray.parseArray("[" + res.getDevice_ids() + "]", Long.class);
            }
            if (null != res.getUser_ids()) {
                userIds = JSONArray.parseArray("[" + res.getUser_ids() + "]", Long.class);
            }
            String duration[] = res.getDuration().split(",");
            Integer hourInt = Integer.valueOf(duration[0]);
            Integer minuteInt = Integer.valueOf(duration[1]);

            restConfReq.setName(res.getName());
            restConfReq.setStartTime(new Long(0));//DateUtil.StringToDate(res.getDate(), DateUtil.YMDHMS).getTime()
            restConfReq.setDuration(new Integer(hourInt * 3600000 + minuteInt + 60000).longValue());
            restConfReq.setLecturerEpId(null == res.getDevice_id() ? null : res.getDevice_id().longValue());//22172  21554
            restConfReq.setLecturerUserId(null == res.getUser_id() ? null : res.getUser_id().longValue());
            restConfReq.setLayout("auto");//会议的分屏模式
            restConfReq.setProfile("SVC");//会议质量
            restConfReq.setEndpointIds(deviceIds);
            restConfReq.setUserIds(userIds);
            if (null != res.getUser_number()) {
                restConfReq.setMaxPartyCount(res.getUser_number());
            }
            restConfReq.setRecordingLayout("oneByOne");
            restConfReq.setSubtitle(new RestSubtitle());
            ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_START, restConfReq);
            if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
                obj[0] = true;
                obj[1] = ((RestConf) returnInfo.getReturnData()).getId();

                //如果是互动课堂，调用设置直播分屏 1*1；听课教师1*1；主讲设3*3 所有听课禁言
                if (res.getType().equals(2)) {
                    dealPeerLayout(restConfReq, (Long) obj[1], res.getIsLive());
                }
            } else {
                obj[0] = false;
                obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
            }
        } else {
            //TODO
            iSoBaioService.sendMsgToBaio(null, null);

            obj[0] = true;
        }
        return obj;
    }

    /**
     * 如果开启直播，处理直播逻辑
     *
     * @param uuid
     * @return 流媒体token,  流媒体url
     */
    public String[] dealLiving(String uuid) {
        String res[] = new String[2];
        String str[] = iCustomerServerService.getCustomerServerByUserInfo();
        ReturnInfo returnInfo = iSoBrsService.getServer(iSoBrsService.SOBRS_LOGIN, uuid, str[0]);
        String token = null;
        if (MessageCode.COMMON_SUCCEED_FLAG == returnInfo.getReturnCode()) {
            token = JSONObject.parseObject((String) returnInfo.getReturnData()).getString("data");
            returnInfo = iSoBrsService.getServer(iSoBrsService.SOBRS_STARTTASK, uuid, str[0]);
            if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
                res[0] = null;
            }
        }
        res[0] = token;
        res[1] = str[1];
        return res;
    }

    //如果是互动课堂，调用设置直播分屏 1*1；听课教室1*1；主讲设3*3 所有听课禁言
    @Override
    public void dealPeerLayout(RestConfReq restConfReq, long ysx_course_id, String isLive) {
        try {
            //先设置主讲端
            iSoMruService.getServer(
                    iSoMruService.CONFERENCE_SETPEERLAYOUT,
                    new RestPartyLayoutReqBean(ysx_course_id, restConfReq.getLecturerEpId(), "3X3", restConfReq.getEndpointIds()));

            //设置远程端
            for (Long device_id : restConfReq.getEndpointIds()) {
                iSoMruService.getServer(
                        iSoMruService.CONFERENCE_SETPEERLAYOUT,
                        new RestPartyLayoutReqBean(ysx_course_id, device_id, "1X1", Arrays.asList(restConfReq.getLecturerEpId())));
            }
            //如果开启直播，设置直播分屏
            if ("1".equals(isLive)) {
                iSoMruService.getServer(
                        iSoMruService.CONFERENCE_SETLIVINGSTREAMLAYOUT,
                        new RestPartyLayoutReqBean(ysx_course_id, restConfReq.getLecturerEpId(), "1X1", Arrays.asList(restConfReq.getLecturerEpId())));

            }

            CourseDeviceMuteReqBean courseDeviceMuteReq = new CourseDeviceMuteReqBean();
            courseDeviceMuteReq.setMuteAudio(true);
            courseDeviceMuteReq.setYsx_course_id(String.valueOf(ysx_course_id));
            //设置禁言
            iSoMruService.getServer(iSoMruService.CONFERENCE_MUTEAUDIOALL, courseDeviceMuteReq);
        } catch (Exception e) {
            log.error("开课设置异常", e);
        }
    }

}