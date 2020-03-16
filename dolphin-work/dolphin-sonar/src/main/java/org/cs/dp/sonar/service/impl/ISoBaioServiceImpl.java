package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.sonar.common.redis.RedisUtil;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.service.ICourseDeviceService;
import org.cs.dp.sonar.service.ICourseService;
import org.cs.dp.sonar.service.IScheduleService;
import org.cs.dp.sonar.service.ISoBaioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 调用radar服务baio相关接口
 */
@Slf4j
@Service
public class ISoBaioServiceImpl implements ISoBaioService {
    @Autowired
    IBaioClient iBaioClient;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ICourseDeviceService iCourseDeviceService;
    @Autowired
    private ICourseService iCourseService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IScheduleService iScheduleService;

    @Override
    public ReturnInfo sendMsgToBaio(String msg, String queue) {
        return iBaioClient.sendmsg(msg, queue);
    }

    @Override
    public ReturnInfo recordedBroadcastCourse(String message) {
        ReturnInfo returnInfo = null;
        DeviceServerReqBean param = JSONObject.parseObject(message, DeviceServerReqBean.class);
        switch (param.getSubtype()) {
            case "start_rss_task":
                returnInfo = delaStart(param);
                break;
            case "pause_rss_task":
                returnInfo = pause(param);
                break;
            case "continue_rss_task":
                returnInfo = cont(param);
                break;
            case "stop_rss_task":
                returnInfo = stop(param);
                break;
            case "query_schedule":
                returnInfo = getSchedule(param.getSn());
                break;
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo keepalive(String message, Integer type) {
        try {
            DeviceStateBean param = JSONObject.parseObject(message, DeviceStateBean.class);
            if (1 == type) {
                param.setDate(new Date().getTime());
                redisUtil.addMap(RedisConstant.DEVICE_KEEPALIVE_PATH, param.getSn(), param);
            }else{
                redisUtil.addMap(RedisConstant.BRS_KEEPALIVE_PATH, param.getSn(), param);
            }
            return new ReturnInfo("{\"code\":1000}");
        } catch (Exception e) {
            log.error("设备心跳更新异常", e);
            return new ReturnInfo(MessageCode.EXCEPTION, "更新异常");
        }
    }

    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo delaStart(DeviceServerReqBean param) {
        //查询设备信息
        DeviceServerBean deviceServer = deviceMapper.getInfoByNum(param.getSn());

        String result = iCourseService.exit(deviceServer.getDevice_id());
        if (null != result) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, result);
        }
        CourseEntity course = new CourseEntity();
        course.setCourse_name(param.getTask_name());
        course.setCourse_type(3);
        course.setCreaterId(0);
        course.setProvenance("DeviceServer");
        course.setOrg_id(deviceServer.getOrg_id());
        course.setStart_time(DateUtil.getCurrentDate(DateUtil.YMDHMS));
        course.setLocal_classroomId(deviceServer.getDevice_id());
        course.setUser_number(1);
        //处理会议主体信息
        courseMapper.insertSelective(course);
        CourseSaveResBean courseSave = new CourseSaveResBean();
        courseSave.setCourse_id(course.getCourse_id());
        courseSave.setName(param.getTask_name());
        courseSave.setType(3);
        ReturnInfo returnInfo = iCourseDeviceService.startLiveStreaming(null,
                courseSave, deviceServer.getOrg_id(), deviceServer.getCustomer_id());
        if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, String.valueOf(returnInfo.getReturnData()));
        } else {
            return new ReturnInfo(String.format("{\"code\":1000,\"data\":{\"push_url\":\"%s\"}}", returnInfo.getReturnData()));
        }
    }

    private ReturnInfo pause(DeviceServerReqBean param) {
        getCourseId(param.getSn(), "pause");
        return new ReturnInfo("{\"code\":1000}");
    }

    private ReturnInfo cont(DeviceServerReqBean param) {
        getCourseId(param.getSn(), "creating");
        return new ReturnInfo("{\"code\":1000}");
    }

    private ReturnInfo stop(DeviceServerReqBean param) {
        CourseEndReqBean courseEndReq = new CourseEndReqBean();
        DeviceServerBean deviceServer = deviceMapper.getInfoByNum(param.getSn());
        CourseEntity course = courseMapper.selectByDeviceId(deviceServer.getDevice_id());
        courseEndReq.setCourse_id(course.getCourse_id());
        courseEndReq.setOrg_id(course.getOrg_id());
        courseEndReq.setCustomer_id(deviceServer.getCustomer_id());
        try {
            return iCourseService.end(courseEndReq);
        } catch (BaseException e) {
            log.error("停止失败", e);
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "结束失败！");
        }
    }

    private void getCourseId(String sn, String state) {
        DeviceServerBean deviceServer = deviceMapper.getInfoByNum(sn);
        Integer course_id = courseMapper.selectByDeviceId(deviceServer.getDevice_id()).getCourse_id();
        CourseEntity course = new CourseEntity();
        course.setCourse_id(course_id);
        course.setRecord_state(state);
        course.setProvenance(null);
        courseMapper.updateByPrimaryKeySelective(course);
    }

    private ReturnInfo getSchedule(String obj) {
        return iScheduleService.getByDeviceId(obj);
    }

}
