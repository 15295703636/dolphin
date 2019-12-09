package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.InteractionDeviceEntity;

/**
* @ClassName IInteractionDeviceService
* @Description 互动-端管理接口
* @Author LiuJT
* @Date 2019-12-09 03:15:34
**/
public interface IInteractionDeviceService {

    ReturnInfo addInteractionDevice(InteractionDeviceEntity param);

    ReturnInfo delInteractionDevice(Integer param);

    ReturnInfo editInteractionDevice(InteractionDeviceEntity param);

    ReturnInfo getInteractionDevice(RequestPage<SplitPageInfo, Object> param);

}
