package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;
import org.cs.dp.ucenter.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrganizationController
 * @Description 组织信息
 * @Author Liujt
 * @Date 2019/11/22 11:02
 **/
@RestController
@RequestMapping("/org")
@Api(tags = "【组织信息】")
public class OrganizationController {

    @Autowired
    private IOrganizationService iOrganizationService;

    @PostMapping("add")
    @ApiOperation(value = "添加组织信息", notes = "组织信息")
    public ReturnInfo addOrg(@RequestBody OrganizationEntity param) {
        return iOrganizationService.addOrg(param);
    }

    @PostMapping("edit")
    @ApiOperation(value = "编辑组织信息", notes = "组织信息")
    public ReturnInfo editOrg(@RequestBody OrganizationEntity param) {
        return iOrganizationService.editOrg(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除组织信息", notes = "组织信息")
    public ReturnInfo delOrg(@RequestBody int id) {
        return iOrganizationService.delOrg(id);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询所有组织信息(分页参数totals不传这个字段)", notes = "组织信息")
    public ReturnInfo getOrg(@RequestBody RequestPage<SplitPageInfo,OrganizationEntity> param) {
        return iOrganizationService.getOrg(param);
    }

    @PostMapping("getOrgTree")
    @ApiOperation(value = "查询组织树", notes = "组织信息")
    public ReturnInfo getOrgTree(@RequestBody OrganizationEntity param) {
        return iOrganizationService.getOrgTree(param);
    }

}
