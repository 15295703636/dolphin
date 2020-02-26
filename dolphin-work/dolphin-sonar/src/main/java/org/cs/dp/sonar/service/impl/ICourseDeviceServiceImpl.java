package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dp.radar.api.entity.RestEndpoint;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.sonar.domain.CourseDeviceAddReqBean;
import org.cs.dp.sonar.domain.CourseDeviceReqBean;
import org.cs.dp.sonar.domain.RestPartyReqBean;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.service.ICourseDeviceService;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ICourseDeviceServiceImpl
 * @Description 日程-端管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 01:11:55
 **/
@Service
public class ICourseDeviceServiceImpl implements ICourseDeviceService {
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private ISoMruService iSoMruService;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public ReturnInfo addCourseDevice(CourseDeviceAddReqBean param) {
        if (null != param.getDevice_ids() && param.getDevice_ids().size() > 0) {
            Object ysxRes[] = dealAddCourseDevice(param);
            if (!(boolean) ysxRes[0]) {
                return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, (String) ysxRes[1]);
            }
            courseDeviceMapper.insertByDeviceId(param.getCourse_id(), param.getDevice_ids());
        }
        return new ReturnInfo();
    }

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
    public ReturnInfo mute(Integer id) {
        return new ReturnInfo();
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

    private void getDeviceInfo(CourseDeviceReqBean param) {
        List<DeviceEntity> deviceEntity = deviceMapper.selectByPrimaryKey(Arrays.asList(param.getDevice_id()));
        if (deviceEntity.size() == 1) {
            param.setYsx_device_id(deviceEntity.get(0).getYsx_id());
        }
    }
}