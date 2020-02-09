package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ParamValid;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.CheckAddInfoReqBean;
import org.cs.dp.ucenter.domain.ResetSuperPwdBean;
import org.cs.dp.ucenter.domain.SuperUserGetReqBean;
import org.cs.dp.ucenter.domain.UPBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;
import org.cs.dp.ucenter.service.ISuperUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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
    public ReturnInfo login(@RequestBody UPBean param) throws BaseException {
        return iSuperUserService.login(param);
    }

    @PostMapping("loginOut")
    @ApiOperation(value = "退出接口", notes = "平台管理员用户信息")
    public ReturnInfo loginOut(HttpServletRequest request) {
        return iSuperUserService.loginOut(request);
    }

    @PostMapping("delById")
    @ApiOperation(value = "根据ID删除用户信息", notes = "平台管理员用户信息")
    public ReturnInfo delById(@RequestBody List<Integer> ids) {
        return iSuperUserService.del(ids);
    }

    @PostMapping("editById")
    @ApiOperation(value = "根据ID修改用户信息", notes = "平台管理员用户信息")
    public ReturnInfo editById(@RequestBody SuperUserEntity param) {
        return iSuperUserService.edit(param);
    }

    @PostMapping("checkAddInfo")
    @ApiOperation(value = "校验用户名手机号是否已注册", notes = "平台管理员用户信息")
    public ReturnInfo checkAddInfo(@RequestBody CheckAddInfoReqBean param) {
        return iSuperUserService.checkAddInfo(param);
    }

    @ParamValid
    @PostMapping("add")
    @ApiOperation(value = "添加客户经理", notes = "平台管理员用户信息")
    public ReturnInfo add(@Valid @RequestBody SuperUserEntity param, BindingResult result) {
        return iSuperUserService.add(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询客户经理(需要根据ID查,传相应的Id,查所有,不需要传)", notes = "平台管理员用户信息")
    public ReturnInfo get(@RequestBody RequestPage<SplitPageInfo, SuperUserGetReqBean> param) {
        return iSuperUserService.getManage(param);
    }

    @PostMapping("upload")
    @ApiOperation(value = "导入客户经理信息", notes = "平台管理员用户信息")
    public ReturnInfo upload(MultipartFile file) throws IOException {
        return iSuperUserService.upload(file);
    }

    /**
     * 如果当前用户id == 入参的Id，说明是自己重置密码，需要验证当前密码
     * 如果不相等，说明是超级管理员重置客户代表密码，不需要验证当前密码
     *
     * @param param
     * @param result
     * @return
     */
    @ParamValid
    @PostMapping("resetPwd")
    @ApiOperation(value = "客户代表/超级管理员用户自己重置密码", notes = "平台管理员用户信息")
    public ReturnInfo resetPwd(@Valid @RequestBody ResetSuperPwdBean param, BindingResult result) {
        return iSuperUserService.resetPwd(param);
    }

    @GetMapping("geCusId")
    @ApiOperation(value = "查询客户代表信息", notes = "平台管理员用户信息")
    public ReturnInfo geCusId() {
        return iSuperUserService.getSuperUserByCusId();
    }

}
