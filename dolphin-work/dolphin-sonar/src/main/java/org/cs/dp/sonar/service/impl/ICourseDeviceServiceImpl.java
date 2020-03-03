package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dp.radar.api.entity.RestConfReq;
import org.cs.dp.radar.api.entity.RestEndpoint;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.mapper.UcenterMapper;
import org.cs.dp.sonar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ICourseDeviceServiceImpl
 * @Description 日程-端管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 01:11:55
 **/
@Slf4j
@Service
public class ICourseDeviceServiceImpl implements ICourseDeviceService {
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private UcenterMapper ucenterMapper;
    @Autowired
    private ISoMruService iSoMruService;
    @Autowired
    private IScheduleService iScheduleService;
    @Autowired
    private ISoBrsService iSoBrsService;
    @Autowired
    private ICustomerServerService iCustomerServerService;

    @Value("${push.flow.url}")
    private String flowUrl;

    @Override
    public ReturnInfo addCourseDevice(CourseDeviceAddReqBean param) {
        //端节点有数据
        if (null != param.getDevice_ids() && param.getDevice_ids().size() > 0) {
            List<Integer> addList = new ArrayList<>();
            List<Integer> delList = new ArrayList<>();
            List<Integer> paramDeviceIds = param.getDevice_ids();
            List<Integer> courses = courseDeviceMapper.selectByCourseDeviceId(param.getCourse_id(), param.getDevice_ids());
            List<CourseDeviceEntity> courses2 = courseDeviceMapper.selectByCourseDeviceId2(param.getCourse_id(), param.getDevice_ids());

            List<Integer> coursesInt = new ArrayList<>();
            courses2.forEach(e -> {
                if (1 != e.getType()) {//排除主讲端
                    coursesInt.add(e.getDevice_id());
                }
            });
            paramDeviceIds.forEach(e -> {
                if (!courses.contains(e)) {
                    addList.add(e);
                }
            });
            coursesInt.forEach(a -> {
                if (!paramDeviceIds.contains(a)) {
                    delList.add(a);
                }
            });
            if (addList.size() > 0) {
                param.setDevice_ids(addList);
                Object obj[] = dealAddCourseDevice(param);
                if ((boolean) obj[0]) {
                    courseDeviceMapper.insertByDeviceId(param.getCourse_id(), addList);
                } else {
                    return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, (String) obj[1]);
                }
            }

            if (delList.size() > 0) {
                for (Integer deviceId : delList) {
                    ReturnInfo returnInfo = delCourseDevice(new CourseDeviceReqBean(
                            param.getYsx_id(),
                            courses2.stream().filter(e -> e.getDevice_id() == deviceId).collect(Collectors.toList()).get(0).getId(),
                            deviceId));
                    if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                        return returnInfo;
                    }
                }
            }
        } else {
            List<CourseResBean> courseResBeans = courseDeviceMapper.selectByCourseId(param.getCourse_id());
            for (CourseResBean course : courseResBeans) {
                ReturnInfo returnInfo = delCourseDevice(new CourseDeviceReqBean(
                        param.getYsx_id(),
                        course.getId(),
                        course.getDevice_id()));
                if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                    return returnInfo;
                }
            }
        }
        return new ReturnInfo();
    }

    /**
     * 调用会捷通接口添加设备
     * @param param
     * @return
     */
    private Object[] dealAddCourseDevice(CourseDeviceAddReqBean param) {
        RestPartyReqBean restPartyReq = new RestPartyReqBean();
        restPartyReq.setYxs_id(param.getYsx_id());
        List<DeviceEntity> devices = deviceMapper.selectByPrimaryKey(param.getDevice_ids());
        List<RestEndpoint> endpoints = new ArrayList<>();
        devices.forEach(device -> {
            RestEndpoint restEndpoint = new RestEndpoint();
            restEndpoint.setId(device.getYsx_id());
            Map map = JSONObject.parseObject(device.getYsx_info(), Map.class);
            restEndpoint.setName((String) map.get("name"));
            restEndpoint.setDeviceSn((String) map.get("deviceSn"));
            restEndpoint.setCallProtocol((String) map.get("callProtocol"));
            endpoints.add(restEndpoint);
        });
        restPartyReq.setEndpoints(endpoints);

        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_ADDPEER, restPartyReq);
        Object obj[] = new Object[2];
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            obj[0] = true;
        } else {
            obj[0] = false;
            obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
        }
        return obj;
    }

    @Override
    public ReturnInfo delCourseDevice(CourseDeviceReqBean param) {
        Object ysxRes[] = dealDel(param);
        if (!(boolean) ysxRes[0]) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, (String) ysxRes[1]);
        }
        courseDeviceMapper.deleteByPrimaryKey(param.getId());
        return new ReturnInfo();
    }

    private Object[] dealDel(CourseDeviceReqBean param) {
        getDeviceInfo(param);
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_REMOVEPEER, param);
        Object obj[] = new Object[2];
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            obj[0] = true;
        } else {
            obj[0] = false;
            obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
        }
        return obj;
    }

    @Override
    public ReturnInfo editCourseDevice(CourseDeviceEntity param) {
        courseDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCourseDevice(Integer id) {
        //List<CourseDeviceEntity> resList = courseDeviceMapper.selectByCourseId(id);
        int a = 1 / 0;
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo mute(CourseDeviceMuteReqBean param) {
        String serverName = null;
        if (!StringUtils.isEmpty(param.getYsx_device_id())) {
            serverName = iSoMruService.CONFERENCE_MUTEAUDIO;
        } else {
            serverName = iSoMruService.CONFERENCE_MUTEAUDIOALL;
        }
        ReturnInfo returnInfo = iSoMruService.getServer(serverName, param);
        if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
            returnInfo = new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo cancelMute(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo connect(CourseDeviceReqBean param) {
        getDeviceInfo(param);
        String serverName = null;
        if (param.isConnect()) {
            serverName = iSoMruService.CONFERENCE_CALLPEER;
        } else {
            serverName = iSoMruService.CONFERENCE_HANGUPPEER;
        }
        ReturnInfo returnInfo = iSoMruService.getServer(serverName, param);
        if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
            returnInfo = new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo sidelines(Integer id) {
        return new ReturnInfo();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo setLecturer(CourseDeviceSetLecturerReqBean param) {
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_SETLECTURER, param);
        if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        }

        courseDeviceMapper.updateType(param.getId(), param.getCourse_id(), "1");
        courseDeviceMapper.updateType(param.getId(), param.getCourse_id(), "2");

        return new ReturnInfo();
    }

    //设置讨论模式
    @Override
    public ReturnInfo setDiscMode(CourseDeviceSpeakReqBean param) {
        ReturnInfo returnInfo;
        int class_state = 2;
        //开启讨论模式
        if (param.isSign()) {
            returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_SETDISCMODE, param.getYsx_course_id());
            if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }

            Long ysx_course_id = Long.parseLong(param.getYsx_course_id());
            Long ysx_device_id = Long.parseLong(param.getYsx_device_id());

            //先设置主讲端分屏
            iSoMruService.getServer(
                    iSoMruService.CONFERENCE_SETPEERLAYOUT,
                    new RestPartyLayoutReqBean(ysx_course_id, ysx_device_id, "3X3", param.getYsx_device_ids()));

            //设置远程端分屏
            for (Long device_id : param.getYsx_device_ids()) {
                iSoMruService.getServer(
                        iSoMruService.CONFERENCE_SETPEERLAYOUT,
                        new RestPartyLayoutReqBean(ysx_course_id, ysx_device_id, "3X3",
                                param.getYsx_device_ids().stream().filter(deviceId -> !param.getYsx_device_ids().contains(device_id)).collect(Collectors.toList())
                        ));
            }
            //如果开启直播，设置直播分屏
            if ("1".equals(param.getIsLive())) {
                param.getYsx_device_ids().add(Long.parseLong(param.getYsx_device_id()));
                iSoMruService.getServer(
                        iSoMruService.CONFERENCE_SETLIVINGSTREAMLAYOUT,
                        new RestPartyLayoutReqBean(ysx_course_id, ysx_device_id, "3X3", param.getYsx_device_ids()));
            }
        } else {//取消讨论模式
            returnInfo = speak(param);
            if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }
            class_state = 1;
        }
        courseMapper.updateClassState(class_state, Integer.valueOf(param.getYsx_course_id()));
        return returnInfo;
    }

    /*//设置分屏
    @Override
    public ReturnInfo setPeerLayout(RestPartyLayoutReqBean param) {
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_SETPEERLAYOUT, param);
        if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
        }
        return new ReturnInfo();
    }*/

    /**
     * 设置发言：直播分屏1*2；主讲看发言1*1；发言看主讲1*1；其他看主讲+发言1*2 其他教师禁言
     * 取消发言：设置成开会时模式
     *
     * @param param
     * @return
     */
    @Override
    public ReturnInfo speak(CourseDeviceSpeakReqBean param) {
        ReturnInfo returnInfo = new ReturnInfo();
        int class_state = 3;
        //发言设置
        if (param.isSign()) {

            //主讲看发言1*1
            returnInfo = iSoMruService.getServer(
                    iSoMruService.CONFERENCE_SETPEERLAYOUT,
                    new RestPartyLayoutReqBean(param.getYsx_course_id(), param.getYsx_device_id(), "1X1", Arrays.asList(param.getYsx_speak_device_id())));
            if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }

            //发言看主讲1*1
            iSoMruService.getServer(
                    iSoMruService.CONFERENCE_SETPEERLAYOUT,
                    new RestPartyLayoutReqBean(param.getYsx_course_id(), param.getYsx_speak_device_id(), "1X1", Arrays.asList(Long.parseLong(param.getYsx_device_id()))));
            if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }

            //其他看主讲+发言1*2
            for (Long id : param.getYsx_device_ids()) {
                ReturnInfo returnInfo2 = iSoMruService.getServer(iSoMruService.CONFERENCE_SETPEERLAYOUT,
                        new RestPartyLayoutReqBean(param.getYsx_course_id(), id, "1X2", Arrays.asList(Long.parseLong(param.getYsx_device_id()), param.getYsx_speak_device_id())));
                if (returnInfo2.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                    return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo2.getReturnData()).getErrorCode()));
                }
            }

            //开启直播,设置直播分屏
            if ("1".equals(param.getIsLive())) {
                List devices = Arrays.asList(param.getYsx_speak_device_id(),
                        Long.parseLong(param.getYsx_device_id())
                );
                devices.addAll(param.getYsx_device_ids());
                iSoMruService.getServer(iSoMruService.CONFERENCE_SETLIVINGSTREAMLAYOUT,
                        new RestPartyLayoutReqBean(param.getYsx_course_id(), null, "1X3", devices));
            }

            //取消发言人静音
            CourseDeviceMuteReqBean courseDeviceMute = new CourseDeviceMuteReqBean();
            courseDeviceMute.setMuteAudio(false);
            courseDeviceMute.setYsx_course_id(String.valueOf(param.getYsx_course_id()));
            courseDeviceMute.setYsx_device_id(String.valueOf(param.getYsx_speak_device_id()));
            returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_MUTEAUDIO, courseDeviceMute);
            if (returnInfo.getReturnCode() != MessageCode.COMMON_SUCCEED_FLAG) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }
        } else {
            //取消发言 还原成上课状态
            RestConfReq restConfReq = new RestConfReq();
            restConfReq.setLecturerEpId(Long.parseLong(param.getYsx_device_id()));
            param.getYsx_device_ids().add(param.getYsx_speak_device_id());
            restConfReq.setEndpointIds(param.getYsx_device_ids());
            iScheduleService.dealPeerLayout(restConfReq, Long.parseLong(param.getYsx_course_id()), param.getIsLive());
            class_state = 1;
        }
        courseMapper.updateClassState(class_state, Integer.valueOf(param.getYsx_course_id()));
        return returnInfo;
    }

    /**
     * 开启直播
     *
     * @param course_id
     * @return
     */
    @Override
    public ReturnInfo startLiveStreaming(String course_id, String type) {
        String uuid = UuidUtil.generateUuid();
        //流媒体token,  流媒体url
        String res[] = iScheduleService.dealLiving(uuid);
        if (StringUtils.isEmpty(res[1])) {
            return new ReturnInfo(MessageCode.Fail_Inter_EduContrl_Platform, "开启直播失败");
        }

        String url = String.format(flowUrl, res[1], uuid, res[0]);
        if (!"3".equals(type)) {
            RestLiveStreamingReqBean reqBean = new RestLiveStreamingReqBean();
            reqBean.setCourser_id(course_id);
            reqBean.setRecord(true);
            reqBean.setProfile("GW_WEBCAST");
            reqBean.setPeoplePlusContentLayoutMode("oneByOne");
            reqBean.setUrl(url);
            ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_STARTLIVESTREAMING, reqBean);
            if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            } else {
                courseMapper.updateLiveState(res[1], uuid, Integer.parseInt(course_id));
            }
        } else {
            courseMapper.updateLiveState(res[1], uuid, Integer.parseInt(course_id));
        }
        return new ReturnInfo(url);
    }

    /**
     * 停止直播
     *
     * @param course_id
     * @return
     */
    @Override
    public ReturnInfo stopLiveStreaming(String course_id) {
        //根据当前用户信息查询流媒体信息 返回：流媒体序列号，流媒体服务地址
        String str[] = iCustomerServerService.getCustomerServerByUserInfo();
        CourseEntity course = courseMapper.selectByIdYsxId(Long.parseLong(course_id));
        //结束流媒体录制服务
        ReturnInfo returnInfo = iSoBrsService.getServer(iSoBrsService.SOBRS_STOPTASK, course.getStream_id(), str[0]);
        if (MessageCode.COMMON_SUCCEED_FLAG == returnInfo.getReturnCode()) {
            //结束会捷通录制
            returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_STOPLIVESTREAMING, course_id);
        }
        //查询录制的信息
        returnInfo = iSoBrsService.getServer(iSoBrsService.SOBRS_QUERYTASK, course.getStream_id(), str[0]);
        return returnInfo;
    }

    private void getDeviceInfo(CourseDeviceReqBean param) {
        List<DeviceEntity> deviceEntity = deviceMapper.selectByPrimaryKey(Arrays.asList(param.getDevice_id()));
        if (deviceEntity.size() == 1) {
            param.setYsx_device_id(deviceEntity.get(0).getYsx_id());
        } else {
            Long ysx_id = ucenterMapper.selectYsxById(param.getDevice_id());
            param.setYsx_device_id(ysx_id);
        }
    }
}