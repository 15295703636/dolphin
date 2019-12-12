package org.cs.dp.ucenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;
import org.cs.dp.ucenter.mapper.UserOrgMapper;
import org.cs.dp.ucenter.service.IUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IUserOrgServiceImpl
* @Description 租户管理实现类
* @Author Liujt
* @Date 2019-12-05 06:45:57
**/
@Service
public class IUserOrgServiceImpl implements IUserOrgService {
    @Autowired
    private UserOrgMapper userOrgMapper;

    @Override
    public ReturnInfo addUserOrg(UserOrgEntity param) {
        userOrgMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delUserOrg(Integer param) {
        userOrgMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editUserOrg(UserOrgEntity param) {
        userOrgMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getUserOrg(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<UserOrgEntity> resList = null;//TODO 分页sql要自己实现 userOrgMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}