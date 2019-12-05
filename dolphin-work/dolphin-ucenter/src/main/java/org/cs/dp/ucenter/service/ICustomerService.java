package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.AddCustomerBean;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;

/**
* @ClassName ICustomerService
* @Description 租户管理接口
* @Author Liujt
* @Date 2019-12-05 04:27:19
**/
public interface ICustomerService {

    ReturnInfo addCustomer(AddCustomerBean param) throws BaseException;

    ReturnInfo delCustomer(Integer param);

    ReturnInfo editCustomer(CustomerEntity param);

    ReturnInfo getCustomer(RequestPage<SplitPageInfo, String> param);

    ReturnInfo getCustomerByManageId(Integer manageId);
}
