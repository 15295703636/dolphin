package org.cs.dp.sonar.controller;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("ALL")
@RestController
public class UserTestController {

    @Autowired
    private IUserClient iUserClient;

    @RequestMapping("/test22")
    private ReturnInfo test(){
        ReturnInfo r =  iUserClient.selectUserByOrg(1,20,1);
        return  r;
    }
}
