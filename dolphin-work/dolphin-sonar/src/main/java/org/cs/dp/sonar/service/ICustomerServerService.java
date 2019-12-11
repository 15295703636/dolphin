package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;

/**
* @ClassName ICustomerServerService
* @Description 租户-服务管理接口
* @Author LiuJT
* @Date 2019-12-11 05:43:31
**/
public interface ICustomerServerService {

    ReturnInfo addCustomerServer(CustomerServerEntity param);

    ReturnInfo delCustomerServer(Integer param);

    ReturnInfo editCustomerServer(CustomerServerEntity param);

    ReturnInfo getCustomerServer(RequestPage<SplitPageInfo, Object> param);

}
