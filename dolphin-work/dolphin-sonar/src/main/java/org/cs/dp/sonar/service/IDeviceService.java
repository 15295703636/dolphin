package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

import java.util.List;

/**
* @ClassName IDeviceService
* @Description 设备/端管理接口
* @Author LiuJT
* @Date 2019-12-06 05:11:20
**/
public interface IDeviceService {

    ReturnInfo addDevice(DeviceEntity param);

    ReturnInfo delDevice(List<Integer> ids);

    ReturnInfo editDevice(DeviceEntity param);

    ReturnInfo getDevice(RequestPage<SplitPageInfo, GetDeviceBean> param);

}
