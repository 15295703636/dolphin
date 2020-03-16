package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.service.IAuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AuthInfoController
 * @Description 租户授权信息
 * @Author Liujt
 * @Date 2019/12/11 14:55
 **/
@RestController
@RequestMapping("authInfo")
@Api(tags = "【租户授权信息管理】")
public class AuthInfoController {

    @Autowired
    private IAuthInfoService iAuthInfoService;

    @PostMapping("get")
    @ApiOperation(value = "查询租户授权信息", notes = "租户授权信息管理")
    public ReturnInfo getAuthInfo(@RequestBody Integer customerId){
        return iAuthInfoService.getAuthInfo(customerId);
    }

}
