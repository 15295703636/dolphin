package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.PostTestBean;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "控制类业务说明；例：【用户信息】")
public class UserTestController {

    @Autowired
    private IUserClient iUserClient;

    @GetMapping("/getTest/{page}")
    @ApiOperation(value = "方法业务说明；例：测试Get方法", notes = "用户信息")
    public ReturnInfo GetTest(@PathVariable(value = "page") Integer page) {
        return iUserClient.selectUserByOrg(page, 20, 1);
    }

    @PostMapping("/postTest")
    @ApiOperation(value = "方法业务说明；例：测试Post方法", notes = "用户信息")
    public ReturnInfo PostTest(@RequestBody PostTestBean param) {
        return new ReturnInfo();
    }
}
