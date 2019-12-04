package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ParamValid;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.service.ISuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName SuperUserController
 * @Description 平台管理员用户信息
 * @Author Liujt
 * @Date 2019/11/22 14:11
 **/
@RestController
@RequestMapping("/superUser")
@Api(tags = "【平台管理员用户信息】")
public class SuperUserController {

    @Autowired
    private ISuperUserService iSuperUserService;

    @PostMapping("login")
    @ApiOperation(value = "登录接口", notes = "平台管理员用户信息")
    public ReturnInfo login(@RequestBody UPBean param) {
        return iSuperUserService.login(param);
    }

    @PostMapping("loginOut")
    @ApiOperation(value = "退出接口", notes = "平台管理员用户信息")
    public ReturnInfo loginOut(HttpServletRequest request) {
        return iSuperUserService.loginOut(request);
    }

    @PostMapping("delById")
    @ApiOperation(value = "根据ID删除用户信息", notes = "平台管理员用户信息")
    public ReturnInfo delById(@RequestBody int id) {
        return iSuperUserService.del(id);
    }

    @PostMapping("editById")
    @ApiOperation(value = "根据ID修改用户信息", notes = "平台管理员用户信息")
    public ReturnInfo editById(@RequestBody SuperUserEntity param) {
        return iSuperUserService.edit(param);
    }

    @ParamValid
    @PostMapping("add")
    @ApiOperation(value = "添加用户", notes = "平台管理员用户信息")
    public ReturnInfo add(@Valid @RequestBody SuperUserEntity param, BindingResult result) {
        return iSuperUserService.add(param);
    }
}
