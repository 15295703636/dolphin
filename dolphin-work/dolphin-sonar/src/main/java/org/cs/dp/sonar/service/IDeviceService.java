package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

/**
* @ClassName IDeviceService
* @Description 设备-端管理接口
* @Author LiuJT
* @Date 2019-12-10 08:04:05
**/
public interface IDeviceService {

    ReturnInfo addDevice(DeviceEntity param);

    ReturnInfo delDevice(Integer param);

    ReturnInfo editDevice(DeviceEntity param);

    ReturnInfo getDevice(RequestPage<SplitPageInfo, GetDeviceBean> param);

}
