package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

/**
 * @ClassName IDeviceService
 * @Description 设备管理接口
 * @Author Liujt
 * @Date 2019-11-29 02:25:53
 **/
public interface IDeviceService {

    ReturnInfo addDevice(DeviceEntity param);

    ReturnInfo delDevice(Integer param);

    ReturnInfo editDevice(DeviceEntity param);

    ReturnInfo getDevice(RequestPage<SplitPageInfo, Object> param);

}
