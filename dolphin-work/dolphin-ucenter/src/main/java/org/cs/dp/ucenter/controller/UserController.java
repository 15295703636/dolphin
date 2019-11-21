package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(tags = "【用户信息】")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("login")
    @ApiOperation(value = "登录接口", notes = "用户信息")
    public ReturnInfo login(@RequestBody UPBean param) {
        return iUserService.login(param);
    }

    @PostMapping("loginOut")
    @ApiOperation(value = "退出接口", notes = "用户信息")
    public ReturnInfo loginOut(HttpServletRequest request) {
        return iUserService.loginOut(request);
    }

    @PostMapping("getUsersByOrg")
    @ApiOperation(value = "根据组织查询用户列表", notes = "用户信息")
    public ReturnInfo getUsersByOrg() {
        return null;
    }

    @PostMapping("delUserById")
    @ApiOperation(value = "根据ID删除用户信息", notes = "用户信息")
    public ReturnInfo delUserById(@RequestBody String id) {
        return null;
    }

    @PostMapping("editUserById")
    @ApiOperation(value = "根据ID修改用户信息", notes = "用户信息")
    public ReturnInfo editUserById() {
        return null;
    }

}
