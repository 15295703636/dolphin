package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.*;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.AddUserBean;
import org.cs.dp.ucenter.domain.OrgIdAndTokenBean;
import org.cs.dp.ucenter.domain.ResetPwdBean;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.UserEntity;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "【用户信息】")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ParamValid
    @PostMapping("login")
    @ApiOperation(value = "登录接口", notes = "用户信息")
    public ReturnInfo login(@Valid @RequestBody UPBean param, BindingResult result) throws BaseException {
        return iUserService.login(param);
    }

    @PostMapping("getUserInfo")
    @ApiOperation(value = "获取用户信息", notes = "用户信息")
    public ReturnInfo<UserInfo> getUserInfo() {
        return iUserService.getUserInfo();
    }

    @PostMapping("loginOut")
    @ApiOperation(value = "退出接口", notes = "用户信息")
    public ReturnInfo loginOut(HttpServletRequest request) {
        return iUserService.loginOut(request);
    }

    @ParamValid
    @PostMapping("resetPwd")
    @ApiOperation(value = "重置密码", notes = "用户信息")
    public ReturnInfo resetPwd(@Valid @RequestBody ResetPwdBean param, BindingResult result) {
        return iUserService.resetPwd(param);
    }

    @ParamValid
    @PostMapping("add")
    @ApiOperation(value = "添加用户信息", notes = "用户信息")
    public ReturnInfo add(@Validated @RequestBody AddUserBean param, BindingResult result) {
        return iUserService.add(param, false);
    }

    @PostMapping("delUserById")
    @ApiOperation(value = "根据ID删除用户信息", notes = "用户信息")
    public ReturnInfo delUserById(@RequestBody List<Integer> userIds) {
        return iUserService.del(userIds);
    }

    @PostMapping("editUserById")
    @ApiOperation(value = "根据ID修改用户信息", notes = "用户信息")
    public ReturnInfo editUserById(@RequestBody UserEntity param) {
        return iUserService.edit(param);
    }

    @PostMapping("getUsersByOrgId")
    @ApiOperation(value = "组织Id查询用户列表", notes = "用户信息")
    public ReturnInfo getUsersByOrgId(@RequestBody RequestPage<SplitPageInfo, OrgIdAndTokenBean> param) {
        return iUserService.getUsersByOrgId(param);
    }

    @PostMapping("upload")
    @ApiOperation(value = "导入用户列表", notes = "用户信息")
    public ReturnInfo upload(MultipartFile file) throws IOException {
        return iUserService.upload(file);
    }

}
