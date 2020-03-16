package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.mapper.CustomerServerMapper;
import org.cs.dp.sonar.mapper.ServerMapper;
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
    @Autowired
    private ServerMapper serverMapper;

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

    /**
     * TODO 到redis中查询状态
     *
     * @param param
     * @return
     */
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

    /**
     * 根据当前用户信息查询流媒体信息
     *
     * @return 流媒体序列号，流媒体服务地址
     */
    @Override
    public String[] getCustomerServerByUserInfo(Integer org_id, Integer customer_id) {
        String res[] = new String[2];
        if (null != ThreadLocalUserInfoUtil.get()) {
            org_id = ThreadLocalUserInfoUtil.get().getOrg_id();
            customer_id = ThreadLocalUserInfoUtil.get().getCustomer_id();
        }
        //查询流媒体地址
        //1.先查询当前节点及父节点是否存在流媒体服务
        //2.如果没有查询公共流媒体服务
        List<CustomerServerEntity> customerServers = customerServerMapper.selectByOrgIdUp(org_id, customer_id);
        if (0 < customerServers.size()) {
            res[0] = customerServers.get(0).getSerial_number();
            res[1] = customerServers.get(0).getServer_ip();
        } else {
            ServerEntity server = serverMapper.selectAll(14).get(0);
            res[0] = server.getSerial_number();
            res[1] = server.getServer_ip();
        }
        return res;
    }
}