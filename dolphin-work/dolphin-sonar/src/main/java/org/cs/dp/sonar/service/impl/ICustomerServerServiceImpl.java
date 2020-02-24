package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;
import org.cs.dp.sonar.mapper.CustomerServerMapper;
import org.cs.dp.sonar.service.ICustomerServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ICustomerServerServiceImpl
 * @Description 租户-服务管理实现类
 * @Author LiuJT
 * @Date 2019-12-11 05:43:31
 **/
@Service
public class ICustomerServerServiceImpl implements ICustomerServerService {
    @Autowired
    private CustomerServerMapper customerServerMapper;

    @Override
    public ReturnInfo addCustomerServer(CustomerServerEntity param) {
        param.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        customerServerMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCustomerServer(Integer param) {
        customerServerMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCustomerServer(CustomerServerEntity param) {
        customerServerMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCustomerServer(RequestPage<SplitPageInfo, Integer> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CustomerServerEntity> resList = customerServerMapper.selectByOrgId(
                param.getInfo(), ThreadLocalUserInfoUtil.get().getCustomer_id());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}