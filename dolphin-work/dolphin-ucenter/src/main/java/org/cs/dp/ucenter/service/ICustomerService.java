package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.AddCustomerBean;
import org.cs.dp.ucenter.domain.AddCustomerUserBean;
import org.cs.dp.ucenter.domain.CustomerByNameAndStateReqBean;
import org.cs.dp.ucenter.domain.EditStatusBean;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;

import java.util.List;

/**
* @ClassName ICustomerService
* @Description 租户管理接口
* @Author Liujt
* @Date 2019-12-05 04:27:19
**/
public interface ICustomerService {

    ReturnInfo checkAddInfo(String param,Integer id);

    ReturnInfo addCustomer(AddCustomerBean param) throws BaseException;

    ReturnInfo addAdminUser(AddCustomerUserBean param) throws BaseException;

    ReturnInfo delCustomer(List<Integer> param);

    ReturnInfo editCustomer(CustomerEntity param);

    ReturnInfo getCustomer(RequestPage<SplitPageInfo, CustomerByNameAndStateReqBean> param);

    ReturnInfo getCustomerByManageId(Integer manageId);

    ReturnInfo editStatus(EditStatusBean param);

    ReturnInfo getCusAdminInfo(Integer id);
}
