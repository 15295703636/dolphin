package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.UserOrgEntity;
import org.cs.dp.ucenter.service.IUserOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName UserOrgController
* @Description 租户管理
* @Author Liujt
* @Date 2019-12-05 06:45:57
**/
@RestController
@RequestMapping("userOrg")
@Api(tags = "【用户组织关系管理】")
public class UserOrgController {
    @Autowired
    private IUserOrgService iUserOrgService;

    @PostMapping("add")
    @ApiOperation(value = "添加用户组织关系管理", notes = "用户组织关系管理")
    public ReturnInfo addUserOrg(@RequestBody UserOrgEntity param){
        return iUserOrgService.addUserOrg(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除用户组织关系管理", notes = "用户组织关系管理")
    public ReturnInfo delUserOrg(@RequestBody Integer id){
        return iUserOrgService.delUserOrg(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改用户组织关系管理", notes = "用户组织关系管理")
    public ReturnInfo editUserOrg(@RequestBody UserOrgEntity param){
        return iUserOrgService.editUserOrg(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询用户组织关系管理", notes = "用户组织关系管理")
    public ReturnInfo getUserOrg(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iUserOrgService.getUserOrg(param);
    }
}
