package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.ucenter.common.Constant;
import org.cs.dp.ucenter.domain.*;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;
import org.cs.dp.ucenter.mapper.CustomerMapper;
import org.cs.dp.ucenter.mapper.OrganizationMapper;
import org.cs.dp.ucenter.mapper.UserOrgMapper;
import org.cs.dp.ucenter.service.ICustomerService;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName ICustomerServiceImpl
 * @Description 租户管理实现类
 * @Author Liujt
 * @Date 2019-12-05 04:27:19
 **/
@Slf4j
@Service
public class ICustomerServiceImpl implements ICustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserOrgMapper userOrgMapper;

    /**
     * 1.添加租户信息
     * 2.默认添加组织信息
     * 3.默认添加租户admin信息
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo addCustomer(AddCustomerBean param) throws BaseException {
        ReturnInfo checkRes = checkAddInfo(param.getCustomer_name(), null);
        if (MessageCode.COMMON_SUCCEED_FLAG != checkRes.getReturnCode()) {
            return checkRes;
        }
        //添加租户信息
        CustomerEntity customer = JSONObject.parseObject(JSON.toJSONString(param), CustomerEntity.class);
        customer.setManage_id(ThreadLocalUserInfoUtil.get().getUser_id());
        customerMapper.insertSelective(customer);

        //如果存在，则不添加
        if (0 == organizationMapper.selectByCusIdCou(customer.getId())) {
            //添加组织信息
            OrganizationEntity org = new OrganizationEntity();
            org.setCustomer_id(customer.getId());
            org.setOrg_name(param.getCustomer_name() + "组织");
            org.setOrg_preid(0);
            organizationMapper.insertSelective(org);
        }
        return new ReturnInfo(customer.getId());
    }

    @Override
    public ReturnInfo checkAddInfo(String customer_name, Integer id) {
        if (0 < customerMapper.selectByUserNameCou(customer_name, id)) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.CUSTOMER_NAME_EXIST_MSG);
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCusAdminInfo(Integer id) {
        UserEntity userEntity = customerMapper.getCusAdminInfo(id);
        userEntity.setUser_pwd(null);
        return new ReturnInfo(userEntity);
    }

    /**
     * 添加租户管理员用户
     *
     * @param param
     * @return
     * @throws BaseException
     */
    public ReturnInfo addAdminUser(AddCustomerUserBean param) throws BaseException {
        List<OrganizationEntity> orgs = organizationMapper.getList(new OrganizationEntity(param.getCustomer_id()));
        if (1 != orgs.size()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_NORMAL, "已存在多条组织信息,暂时无法添加管理员信息,如有疑问请联系管理员!");
        }
        //添加用户
        AddUserBean user = new AddUserBean();
        user.setRole_id(1);
        user.setOrg_id(orgs.get(0).getOrg_id());
        user.setUser_name(param.getUser_name());
        user.setUser_pwd(param.getUser_pwd());
        ReturnInfo returnInfo = iUserService.add(user, true);
        if (MessageCode.COMMON_SUCCEED_FLAG != returnInfo.getReturnCode()) {
            throw new BaseException(null, returnInfo.getMsg());
        }

        //添加用户个组织关系
        userOrgMapper.insertSelective(new UserOrgEntity(null, orgs.get(0).getOrg_id(), user.getUser_id()));
        return new ReturnInfo();
    }


    @Override
    public ReturnInfo delCustomer(List<Integer> param) {
        customerMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCustomer(CustomerEntity param) {
        if (null == param.getId() || 0 == param.getId()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, Constant.CUSTOMER_ID_ISEMPTY_MSG);
        }
        customerMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo author(CustomerAuthorBean param) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(param.getId());
        customerEntity.setCustomer_start_time(DateUtil.StringToDate(param.getCustomer_start_time(), DateUtil.YMDHMS));
        customerEntity.setCustomer_end_time(DateUtil.StringToDate(param.getCustomer_end_time(), DateUtil.YMDHMS));
        customerMapper.updateByPrimaryKeySelective(customerEntity);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCustomer(RequestPage<SplitPageInfo, CustomerByNameAndStateReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CustomerEntity> resList = customerMapper.selectByObj(param.getInfo().getCustomer_name(),
                param.getInfo().getCustomer_status());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo getCustomerByManageId(Integer manageId) {
        return new ReturnInfo(customerMapper.selectByManageId(manageId));
    }

    @Override
    public ReturnInfo editStatus(EditStatusBean param) {
        if (1 != param.getCustomer_status() && 2 != param.getCustomer_status()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "用户状态信息有误!");
        }
        int res = customerMapper.updateStatusByKey(param);
        log.info("{}条用户状态更新为{}", res, param.getCustomer_status());
        return new ReturnInfo();
    }
}