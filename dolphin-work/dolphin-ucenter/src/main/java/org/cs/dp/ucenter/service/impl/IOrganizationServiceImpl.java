package org.cs.dp.ucenter.service.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.cs.dp.ucenter.mapper.OrganizationMapper;
import org.cs.dp.ucenter.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IOrganizationServiceImpl
 * @Description 组织信息维护
 * @Author Liujt
 * @Date 2019/11/22 11:05
 **/
@Slf4j
@Service
public class IOrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public ReturnInfo addOrg(OrganizationEntity param) {
        organizationMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editOrg(OrganizationEntity param) {
        organizationMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delOrg(int id) {
        organizationMapper.deleteByPrimaryKey(id);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getOrg(RequestPage<SplitPageInfo, OrganizationEntity> param) {
        if (null != param.getPage()) {
            PageHelper.startPage(param.getPage().getCurrPage(), param.getPage().getPerPageNum());
        }
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<OrganizationEntity> list = organizationMapper.getList(param.getInfo());
        PageInfo p = new PageInfo(list);
        param.getPage().setTotals((int) p.getTotal());
        return new ReturnInfo(param.getPage(), list);
    }

    @Override
    public ReturnInfo getOrgTree(OrganizationEntity param) {
        List<OrganizationEntity> list = organizationMapper.getList(param);
        list = listGetTree(list);
        log.info("组织树：{}", JSON.toJSONString(list));
        return new ReturnInfo(list);
    }

    private static List<OrganizationEntity> listGetTree(List<OrganizationEntity> list) {
        List<OrganizationEntity> treeList = new ArrayList<>();
        for (OrganizationEntity tree : list) {
            //找到根
            if (tree.getOrg_preid() == 0) {
                treeList.add(tree);
            }
            //找到子
            for (OrganizationEntity treeNode : list) {
                if (treeNode.getOrg_preid() == tree.getOrg_id()) {
                    if (tree.getNode() == null) {
                        tree.setNode(new ArrayList<>());
                    }
                    tree.getNode().add(treeNode);
                }
            }
        }
        return treeList;
    }

}
