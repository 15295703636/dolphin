package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ParamValid;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.UserEntity;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags = "【用户信息】")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ParamValid
    @PostMapping("login")
    @ApiOperation(value = "登录接口", notes = "用户信息")
    public ReturnInfo login(@Valid @RequestBody UPBean param, BindingResult result) {
        return iUserService.login(param);
    }

    @PostMapping("loginOut")
    @ApiOperation(value = "退出接口", notes = "用户信息")
    public ReturnInfo loginOut(HttpServletRequest request) {
        return iUserService.loginOut(request);
    }

    @ParamValid
    @PostMapping("add")
    @ApiOperation(value = "添加用户信息", notes = "用户信息")
    public ReturnInfo add(@Valid @RequestBody UserEntity param, BindingResult result) {
        return iUserService.add(param);
    }

    @PostMapping("delUserById")
    @ApiOperation(value = "根据ID删除用户信息", notes = "用户信息")
    public ReturnInfo delUserById(@RequestBody int id) {
        return iUserService.del(id);
    }

    @PostMapping("editUserById")
    @ApiOperation(value = "根据ID修改用户信息", notes = "用户信息")
    public ReturnInfo editUserById(@RequestBody UserEntity param) {
        return iUserService.edit(param);
    }


    @PostMapping("getUsersByOrgId")
    @ApiOperation(value = "组织Id查询用户列表", notes = "租户关联信息")
    public ReturnInfo getUsersByOrgId(@RequestBody RequestPage<SplitPageInfo, Integer> param) {
        return iUserService.getUsersByOrgId(param);
    }

}
