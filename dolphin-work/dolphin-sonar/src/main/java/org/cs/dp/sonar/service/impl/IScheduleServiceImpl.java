package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.ScheduleOneDeviceBean;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.mapper.ScheduleDeviceMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.cs.dp.sonar.service.IScheduleService;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private ICourseService iCourseService;
    @Autowired
    private ScheduleDeviceMapper scheduleDeviceMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ISoMruService iSoMruService;

    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addSchedule(ScheduleArrayBean param) {

        if (null == param.getDevice_id() && null == param.getUser_id()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "请选择");
        }

        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        param.setCreate_user_id(ThreadLocalUserInfoUtil.get().getUser_id());
        scheduleMapper.insertSelective(param);

        //处理端/软终端数据
        dealDevice(param, true);

        if (param.isNew_start()) {
            start(param.getId());
        }
        return new ReturnInfo();
    }

    private void dealDevice(ScheduleArrayBean param, boolean isEdit) {
        scheduleDeviceMapper.deleteByScheduleId(param.getId());
        if (isEdit) {
            //处理端/软终端数据
            List<ScheduleDeviceEntity> scheduleDevices = new ArrayList<>();
            if (!param.getType().equals(3)) {
                if (null != param.getDeviceIds()) {
                    param.getDeviceIds().forEach(e -> scheduleDevices.add(new ScheduleDeviceEntity(e, param.getId(), 0, 1)));
                }
                if (null != param.getUserIds()) {
                    param.getUserIds().forEach(e -> scheduleDevices.add(new ScheduleDeviceEntity(e, param.getId(), 1, 1)));
                }
            }
            if (null != param.getDevice_id()) {
                scheduleDevices.add(new ScheduleDeviceEntity(param.getDevice_id(), param.getId(), 0, 0));
            } else {
                scheduleDevices.add(new ScheduleDeviceEntity(param.getUser_id(), param.getId(), 1, 0));
            }
            scheduleDeviceMapper.insertBatch(scheduleDevices);
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
            start(param.getId());
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

    @Override
    public ReturnInfo start(Integer id) {
        Object ysxRes[] = dealStart(id);
        if (!(boolean) ysxRes[0]) {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, (String) ysxRes[1]);
        }
        return iCourseService.startSchedule(id, (Long) ysxRes[1]);
    }


    /**
     * @param id
     * @return
     */
    private Object[] dealStart(Integer id) {
        ScheduleOneDeviceBean res = scheduleMapper.getStartInfoById(id);
        List<Long> userIds = new ArrayList<>();
        List<Long> deviceIds = new ArrayList<>();
        if (!res.getType().equals(3)) {
            if (null != res.getDevice_ids()) {
                deviceIds = JSONArray.parseArray("[" + res.getDevice_ids() + "]", Long.class);
            }
            if (null != res.getUser_ids()) {
                userIds = JSONArray.parseArray("[" + res.getUser_ids() + "]", Long.class);
            }
        }
        String duration[] = res.getDuration().split(",");
        Integer hourInt = Integer.valueOf(duration[0]);
        Integer minuteInt = Integer.valueOf(duration[1]);

        RestConfReq restConfReq = new RestConfReq();
        restConfReq.setName(res.getName());
        restConfReq.setStartTime(new Long(0));//DateUtil.StringToDate(res.getDate(), DateUtil.YMDHMS).getTime()
        restConfReq.setDuration(new Integer(hourInt * 3600000 + minuteInt + 60000).longValue());
        restConfReq.setLecturerEpId(null == res.getDevice_id() ? null : res.getDevice_id().longValue());//22172  21554
        restConfReq.setLecturerUserId(null == res.getUser_id() ? null : res.getUser_id().longValue());
        restConfReq.setLayout("auto");//会议的分屏模式
        restConfReq.setProfile("SVC");//会议质量
        restConfReq.setEndpointIds(deviceIds);
        restConfReq.setUserIds(userIds);
        restConfReq.setMaxPartyCount(res.getUser_number());
        restConfReq.setRecordingLayout("oneByOne");
        restConfReq.setSubtitle(new RestSubtitle());
        //restConfReq.setPassword("123456");
        //restConfReq.setDescription("测试开启会议");
        //restConfReq.setRoomId(null);
        //restConfReq.setUseRandomNumericId();
        //restConfReq.setRedialingEnabled();
        //restConfReq.setRecordingEnabled();
        //restConfReq.setRecordingProfile();
        //restConfReq.setLiveStreamingUrl();
        //restConfReq.setAdminUserId();
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.CONFERENCE_START, restConfReq);
        Object obj[] = new Object[2];
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            obj[0] = true;
            obj[1] = ((RestConf) returnInfo.getReturnData()).getId();
        } else {
            obj[0] = false;
            obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
        }
        return obj;
    }
}