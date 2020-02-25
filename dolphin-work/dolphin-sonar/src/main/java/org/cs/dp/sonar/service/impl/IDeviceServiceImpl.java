package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.GetNameByCode;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.RestEndpoint;
import org.cs.dp.radar.api.entity.RestEndpointReq;
import org.cs.dp.radar.api.entity.RestError;
import org.cs.dp.sonar.domain.EndpointReqBean;
import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.mapper.DeviceMapper;
import org.cs.dp.sonar.service.IDeviceService;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName IDeviceServiceImpl
 * @Description 设备-端管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 08:04:05
 **/
@Service
public class IDeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private ISoMruService iSoMruService;

    @Override
    public ReturnInfo addDevice(DeviceEntity param) {
        param.setCreate_user_id(ThreadLocalUserInfoUtil.get().getUser_id());
        Object ysxRes[] = dealAdd(param);
        if ((boolean) ysxRes[0]) {
            param.setYsx_id(((RestEndpoint) ysxRes[1]).getId());
            param.setYsx_info(JSON.toJSONString(ysxRes[1]));
        } else {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, (String) ysxRes[1]);
        }
        deviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    /**
     * 处理运云视讯添加接口
     *
     * @param param
     * @return
     */
    private Object[] dealAdd(DeviceEntity param) {
        RestEndpointReq restEndpointReq = dealYsxParam(param);
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.POINT_ADD, restEndpointReq);
        Object obj[] = new Object[2];
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            obj[0] = true;
            obj[1] = returnInfo.getReturnData();
        } else {
            obj[0] = false;
            obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
        }
        return obj;
    }

    @Override
    public ReturnInfo delDevice(List<Integer> param) {
        Object ysxRes[] = null;
        for (Integer id : param) {
            ysxRes = dealDel(id);
            if ((boolean) ysxRes[0]) {
                deviceMapper.deleteByPrimaryKey(param);
            } else {
                return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, (String) ysxRes[1]);
            }
        }
        return new ReturnInfo();
    }

    private Object[] dealDel(Integer param) {
        List<DeviceEntity> devices = deviceMapper.selectByPrimaryKey(Arrays.asList(param));
        Long ysx_id;
        Object obj[] = new Object[2];
        if (1 == devices.size()) {
            ysx_id = devices.get(0).getYsx_id();
            ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.POINT_DEL, ysx_id);
            if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
                obj[0] = true;
            } else {
                obj[0] = false;
                obj[1] = GetNameByCode.getNameByCode(((RestError) returnInfo.getReturnData()).getErrorCode());
            }
        } else {
            obj[0] = false;
            obj[1] = "ID错误，请刷新裂变并重试!";
        }
        return obj;
    }

    @Override
    public ReturnInfo editDevice(DeviceEntity param) {
        Object ysxRes[] = dealEdit(param);
        if ((boolean) ysxRes[0]) {
            param.setYsx_id((Long) ysxRes[1]);
        } else {
            return new ReturnInfo(MessageCode.Fail_Inter_RecordServer, (String) ysxRes[1]);
        }
        deviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    private EndpointReqBean dealYsxParam(DeviceEntity param) {
        EndpointReqBean restEndpointReq = new EndpointReqBean();
        restEndpointReq.setSystemName(param.getName());
        restEndpointReq.setIp(param.getIp());
        restEndpointReq.setCallProtocol(GetNameByCode.getNameByCode(param.getCall_protocol()));
        restEndpointReq.setSipUrl(param.getSip());
        restEndpointReq.setDeviceSn(param.getDevice_serial_number());
        restEndpointReq.setEndpointType("OTHER");
        restEndpointReq.setE164(param.getE_code());
        return restEndpointReq;
    }

    /**
     * 处理云视讯编辑接口
     *
     * @param param
     * @return
     */
    private Object[] dealEdit(DeviceEntity param) {
        EndpointReqBean restEndpointReq = dealYsxParam(param);
        restEndpointReq.setYsx_id(deviceMapper.selectByPrimaryKey(Arrays.asList(param.getDevice_id())).get(0).getYsx_id());
        ReturnInfo returnInfo = iSoMruService.getServer(iSoMruService.POINT_UPDATE, restEndpointReq);
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
    public ReturnInfo getDevice(RequestPage<SplitPageInfo, GetDeviceBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<DeviceEntity> resList = deviceMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);

    }
}