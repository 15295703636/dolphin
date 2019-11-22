package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.CustomerEntity;
import org.cs.dp.ucenter.mapper.CustomerMapper;
import org.cs.dp.ucenter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName IcustomerServiceImpl
 * @Description 租户信息
 * @Author Liujt
 * @Date 2019/11/21 19:50
 **/
@Service
public class ICustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ReturnInfo addCustomer(CustomerEntity param) {
        customerMapper.insert(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCustomer(CustomerEntity param) {
        customerMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCustomer(int id) {
        customerMapper.deleteByPrimaryKey(id);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCustomer(RequestPage<SplitPageInfo, CustomerEntity> param) {
        if (param.getPage() != null) {
            PageHelper.startPage(param.getPage().getCurrPage(), param.getPage().getPerPageNum());
        }
        List<CustomerEntity> list = customerMapper.getList(param.getInfo());
        PageInfo p = new PageInfo(list);
        param.getPage().setTotals((int) p.getTotal());
        return new ReturnInfo(new RequestPage(param.getPage(),list));
    }
}
