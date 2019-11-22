package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.CustomerEntity;

public interface ICustomerService {
    ReturnInfo addCustomer(CustomerEntity param) ;

    ReturnInfo editCustomer(CustomerEntity param);

    ReturnInfo delCustomer( int id);

    ReturnInfo getCustomer(RequestPage<SplitPageInfo,CustomerEntity> param) ;
}
