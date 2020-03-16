package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
import org.cs.dp.sonar.common.redis.RedisUtil;
import org.cs.dp.sonar.config.ThreadAsyncConfig;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.*;
import org.cs.dp.sonar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.plugin2.message.Message;

import java.util.*;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

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
    private RedisUtil redisUtil;

    @Value("${device.timeOut}")
    private Integer deviceTimeOut;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addSchedule(ScheduleArrayBean param) throws BaseException {

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
        editDaveStart(param.isNew_start(), param.getId());
        return new ReturnInfo();
    }

    /**
     * 处理端/软终端数据
     *
     * @param param
     * @param isEdit
     */
    private void dealDevice(ScheduleArrayBean param, boolean isEdit) {
        scheduleDeviceMapper.deleteByScheduleId(param.getId());
        if (null != param.getType() && !param.getType().equals(3) && isEdit) {
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

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo delSchedule(Integer param) {
        ScheduleArrayBean scheduleArray = new ScheduleArrayBean();
        scheduleArray.setId(param);
        dealDevice(scheduleArray, false);
        scheduleMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo editSchedule(ScheduleArrayBean param) throws BaseException {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        scheduleMapper.updateByPrimaryKeySelective(param);
        dealDevice(param, true);
        editDaveStart(param.isNew_start(), param.getId());
        return new ReturnInfo();
    }

    /**
     * 统一判断处理编辑/保存时是否启动操作
     *
     * @param new_start
     * @param id
     * @throws BaseException
     */
    private void editDaveStart(boolean new_start, Integer id) throws BaseException {
        if (new_start) {
            if (MessageCode.COMMON_SUCCEED_FLAG != start(new ScheduleStartReqBean(id, 0)).getReturnCode()) {
                throw new BaseException("启动日程失败", "启动日程失败");
            }
        }
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
    public ReturnInfo getByDeviceId(Object obj) {
        List<ScheduleOneDeviceBean> resData = scheduleMapper.selectByDeviceId(obj.toString());
        List<ScheduleBaioResBean> res = new ArrayList<>();
        resData.forEach(e -> {
                    ScheduleBaioResBean schedule = new ScheduleBaioResBean();
                    schedule.setType(GetNameByCode.getNameByCode(e.getType()));
                    schedule.setName(e.getName());
                    schedule.setStart_time(e.getDate());

                    schedule.setStart_time(e.getDate());

                    String duration[] = e.getDuration().split(",");
                    schedule.setEnd_time(DateUtil.addDate(
                            DateUtil.addDate(
                                    DateUtil.timeStr2Long(e.getDate(), DateUtil.YMDHMS), Integer.valueOf(duration[0]), DateUtil.HOUR),
                            Integer.valueOf(duration[1]), DateUtil.MINUTE, DateUtil.YMDHMS
                    ));

                    schedule.setLecturer(e.getTeacher_name());
                    schedule.setConference_number(String.valueOf(e.getId()));
                    schedule.setLecturer_device(StringUtils.isEmpty(e.getDeviceName()) ? e.getUserName() : e.getDeviceName());
                    List<String> participants = JSONArray.parseArray("[\"" + e.getDevice_names().replace(",", "\",\"") + "\"]", String.class);
                    if(null != e.getUser_names()){
                        participants.addAll(JSONArray.parseArray("[\"" + e.getUser_names().replace(",", "\",\"") + "\"]", String.class));
                    }
                    schedule.setParticipants(participants);
                    res.add(schedule);
                }
        );
        return new ReturnInfo(String.format("{\"code\": 1000,\"data\": %s}", JSONArray.toJSONString(res)));
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
        //校验端状态
        Object obj = checkDeviceState(param);
        if (obj instanceof String) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, obj.toString());
        } else {
            ScheduleOneDeviceBean scheduleOneDevice = (ScheduleOneDeviceBean) obj;
            //调用会捷通接口开启会议，如果是录播课判断设备是否已被占用
            DealStartResBean startRes = dealStart(scheduleOneDevice);
            log.info("第一步：调用会捷通接口开启会议结果：{}", JSON.toJSONString(startRes));

            //判断日程会议是否成功
            if (!startRes.isResult_sign()) {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, startRes.getError_msg());
            }
            //保存当前日程信息  [操作结果, 课程类型, 是否开启直播, 日程名称]
            CourseSaveResBean courseSave = iCourseService.startCourser(param, startRes.getYsx_course_id());
            log.info("第二步：保存当前日程信息结果：{}", JSON.toJSONString(courseSave));

            ReturnInfo returnInfo = new ReturnInfo();
            //判断保存结果
            if (courseSave.isResult_sign()) {
                //开启直播 || 日程类型为录播课
                if ("1".equals(courseSave.getIsLive()) || "3".equals(courseSave.getType())) {
                    returnInfo = iCourseDeviceService.startLiveStreaming(String.valueOf(startRes.getYsx_course_id()), courseSave, null, null);
                    log.info("第三步：开启是否开启直播处理结果：{}", JSON.toJSONString(returnInfo));
                    if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
                        return returnInfo;
                    }
                }
            } else {
                returnInfo.setReturnCode(MessageCode.COMMON_DATA_UNNORMAL);
                returnInfo.setReturnData(courseSave.getError_msg());
            }
            return returnInfo;
        }
    }

    /**
     * 校验端状态是否正常
     *
     * @param param
     * @return
     */
    private Object checkDeviceState(ScheduleStartReqBean param) {
        ScheduleOneDeviceBean res = null;
        if (1 == param.getHistorySign()) {
            res = courseHistoryMapper.selectByIdResYsx(param.getId());
        } else {
            res = scheduleMapper.getStartInfoById(param.getId());
        }
        List<String> deviceSn = new ArrayList<>();
        List<Integer> deviceIds = JSONArray.parseArray("[" + res.getDevice_ids() + "]", Integer.class);
        deviceIds.add(res.getDevice_id());
        List<DeviceEntity> devices = deviceMapper.selectByYsxId(deviceIds);
        devices.forEach(e -> deviceSn.add(e.getDevice_serial_number()));
        List<DeviceStateBean> deviceStates = redisUtil.getMap(RedisConstant.DEVICE_KEEPALIVE_PATH, deviceSn, DeviceStateBean.class);
        deviceStates = deviceStates.stream().filter(e -> null != e).collect(Collectors.toList());

        Integer type = res.getType();
        List<String> offLine = new ArrayList<>();//未查找到设备状态信息或状态信息更新超时
        List<String> busy = new ArrayList<>();//忙碌(在使用中)
        if (deviceStates.size() > 0) {
            Map<String, DeviceStateBean> deviceMap = deviceStates.stream().collect(Collectors.toMap(DeviceStateBean::getSn, a -> a, (k1, k2) -> k1));
            devices.forEach(e -> {
                if (34 != e.getDevice_type()) {
                    DeviceStateBean deviceState = deviceMap.get(e.getDevice_serial_number());
                    if (null != deviceState) {
                        if ((new Date().getTime() - deviceState.getDate()) > deviceTimeOut) {
                            offLine.add(e.getName());
                        } else if (3 == deviceState.getStatus()) {
                            busy.add(e.getName());
                        } else if (3 != type && 2 == deviceState.getStatus()) {
                            busy.add(e.getName());
                        }
                    } else {
                        offLine.add(e.getName());
                    }
                }
            });
        } else {
            StringBuffer str2 = new StringBuffer();
            devices.forEach(e -> {
                if (34 != e.getDevice_type()) {
                    str2.append("所有设备都未接收到设备状态信息");
                }
            });
            if (!str2.toString().equals("")) {
                return str2;
            }
        }
        String resStr = "";
        if (busy.size() > 0 || offLine.size() > 0) {
            resStr = offLine.size() > 0 ? "未接收到设备状态信息：" + resStr.concat(JSON.toJSONString(offLine)) : resStr;
            resStr = busy.size() > 0 ?
                    StringUtils.isEmpty(resStr) ? "设备使用中：" + resStr.concat(JSON.toJSONString(busy)) : resStr.concat("设备使用中：").concat(JSON.toJSONString(busy)) : resStr;
            return resStr;
        } else {
            return res;
        }
    }

    /**
     * @param param
     * @return 处理结果 会议id
     */
    private DealStartResBean dealStart(ScheduleOneDeviceBean res) {
        DealStartResBean startRes = new DealStartResBean();
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
                startRes.setResult_sign(true);
                startRes.setYsx_course_id(((RestConf) returnInfo.getReturnData()).getId());
                //如果是互动课堂，调用设置直播分屏 1*1；听课教师1*1；主讲设3*3 所有听课禁言
                if (res.getType().equals(2)) {
                    dealPeerLayout(restConfReq, startRes.getYsx_course_id(), res.getIsLive());
                }
            } else {
                startRes.setResult_sign(false);
                startRes.setError_msg(GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode()));
            }
        } else {
            //判断端是否被占用
            String result = iCourseService.exit(res.getDevice_id());
            if (null != result) {
                startRes.setResult_sign(false);
                startRes.setError_msg("设备已占用");
            } else {
                startRes.setResult_sign(true);
            }
        }
        return startRes;
    }

    /**
     * 如果开启直播，处理直播逻辑
     *
     * @param uuid
     * @return 流媒体token,  流媒体url
     */
    public String[] dealLiving(String uuid, Integer org_id, Integer customer_id) {
        String res[] = new String[2];
        String str[] = iCustomerServerService.getCustomerServerByUserInfo(org_id, customer_id);
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