package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.mapper.CustomerMapper;
import org.cs.dp.ucenter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName ICustomerServiceImpl
* @Description 租户管理实现类
* @Author Liujt
* @Date 2019-12-05 04:27:19
**/
@Service
public class ICustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ReturnInfo addCustomer(CustomerEntity param) {
        customerMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCustomer(Integer param) {
        customerMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCustomer(CustomerEntity param) {
        customerMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCustomer(RequestPage<SplitPageInfo, String> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CustomerEntity> resList = customerMapper.selectByObj(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}