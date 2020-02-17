package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.cs.dp.sonar.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ICourseService iCourseService;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ReturnInfo addSchedule(ScheduleArrayBean param) {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        param.setCreate_user_id(ThreadLocalUserInfoUtil.get().getUser_id());
        scheduleMapper.insertSelective(param);
        if (param.isNew_start()) {
            iCourseService.startSchedule(param.getId());
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delSchedule(Integer param) {
        scheduleMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editSchedule(ScheduleArrayBean param) {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        scheduleMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param) {
        List<DeviceEntity> devices = null;
        if (!StringUtils.isEmpty(param.getInfo().getName())) {
            GetDeviceBean getDeviceBean = new GetDeviceBean();
            getDeviceBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
            getDeviceBean.setOrg_id(ThreadLocalUserInfoUtil.get().getOrg_id());
            getDeviceBean.setName(param.getInfo().getName());
            List<Integer> ids = new ArrayList();
            devices = deviceMapper.selectByCondition(getDeviceBean);
            devices.forEach(e -> ids.add(e.getDevice_id()));
            param.getInfo().setDeviceIds(ids);
        }

        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<ScheduleArrayBean> resList = (List<ScheduleArrayBean>) scheduleMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());

        GetDeviceBean getDeviceBean = new GetDeviceBean();
        getDeviceBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        getDeviceBean.setOrg_id(ThreadLocalUserInfoUtil.get().getOrg_id());
        devices = deviceMapper.selectByCondition(getDeviceBean);
        Map<Integer, String> deviceMap = devices.stream().collect(Collectors.toMap(DeviceEntity::getDevice_id, DeviceEntity::getName));

        resList.forEach(e -> {
            List<String> deviceName = new ArrayList<>();
            String duration[] = e.getDuration().split(",");
            e.setDeviceIds(JSONArray.parseArray(e.getDevice_ids(), Integer.class));
            log.info("------------>{}", JSONObject.toJSONString(e));
            if (null != e.getDeviceIds()) {
                e.getDeviceIds().forEach(d -> deviceName.add(deviceMap.get(d)));
                e.setDeviceNames(deviceName);
            }
            e.setDeviceName(deviceMap.get(e.getDevice_id()));
            e.setUserIds(JSONArray.parseArray(e.getUser_ids(), Integer.class));
            e.setDuration_hour(Integer.valueOf(duration[0]));
            e.setDuration_minute(Integer.valueOf(duration[1]));

        });
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo getById(Integer id) {
        GetDeviceBean getDeviceBean = new GetDeviceBean();
        getDeviceBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        getDeviceBean.setOrg_id(ThreadLocalUserInfoUtil.get().getOrg_id());
        List<DeviceEntity> devices = deviceMapper.selectByCondition(getDeviceBean);

        Map<Integer, String> deviceMap = devices.stream().collect(Collectors.toMap(DeviceEntity::getDevice_id, DeviceEntity::getName));

        ScheduleArrayBean res = scheduleMapper.selectById(id);
        String duration[] = res.getDuration().split(",");
        res.setDeviceIds(JSONArray.parseArray(res.getDevice_ids(), Integer.class));
        res.setUserIds(JSONArray.parseArray(res.getUser_ids(), Integer.class));
        res.setDuration_hour(Integer.valueOf(duration[0]));
        res.setDuration_minute(Integer.valueOf(duration[1]));
        res.setDeviceName(deviceMap.get(res.getDevice_id()));

        if (null != res.getDeviceIds()) {
            List<String> deviceName = new ArrayList<>();
            JSONArray.parseArray(res.getDevice_ids(), Integer.class).forEach(d -> deviceName.add(deviceMap.get(d)));
            res.setDeviceNames(deviceName);
        }
        return new ReturnInfo(res);
    }
}