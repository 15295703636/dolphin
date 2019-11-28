package org.cs.dp.sonar.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.jsonrpc.JsonRpcReq;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.radar.api.feign.IBrsClient;
import org.cs.dp.sonar.domain.PostTestBean;
import org.cs.dp.sonar.domain.XxlJobBean;
import org.cs.dp.sonar.service.ISoMruService;
import org.cs.dp.sonar.service.ITestService;
import org.cs.dp.ucenter.api.entity.User;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Api(tags = "控制类业务说明；例：【用户信息】")
public class UserTestController {

    @Autowired
    private IUserClient iUserClient;
    @Autowired
    private ITestService iTestService;
    @Autowired
    private IBaioClient iBaioClient;
    @Autowired
    private IBrsClient iBrsClient;
    @Autowired
    private ISoMruService iSoMruService;

    @GetMapping("/getTest/{page}")
    @ApiOperation(value = "方法业务说明；例：测试Get方法", notes = "用户信息")
    public ReturnInfo GetTest(@PathVariable(value = "page") Integer page) {
        iBaioClient.sendmsg("123","m1");
        iBrsClient.sendmsg("456","m2");
        return  null;
//        return iUserClient.selectUserByOrg(page, 20, 1);
    }

    @PostMapping("/postTest")
    @ApiOperation(value = "方法业务说明；例：测试Post方法", notes = "用户信息")
    public ReturnInfo PostTest(@RequestBody PostTestBean param) {
        return new ReturnInfo();
    }

    @PostMapping("/addJob")
    @ApiOperation(value = "方法业务说明；例：添加定时任务", notes = "用户信息")
    public ReturnInfo addJob(@RequestBody XxlJobBean param){
        return iTestService.addJob(param);
    }

    public static void main(String[] args){
        User user = new User();
        user.setUser_id(1);
        JsonRpcReq jsonRpcReq = new JsonRpcReq();
        jsonRpcReq.setMethod("add_pear");
        jsonRpcReq.setParams(JSONObject.toJSONString(user));
        String json = JsonRpcReq.generateJsonStr(jsonRpcReq);
        System.out.println(json);
    }

    @PostMapping("/mru/login")
    @ApiOperation(value = "Mru用户登录", notes = "用户信息")
    public ReturnInfo mruLogin(@RequestBody RestWebLoginReq restWebLoginReq){
        restWebLoginReq.setAccount("develop_api");
        restWebLoginReq.setIntranet("false");
        restWebLoginReq.setPassword("api@bizconfedu");
        String ip = "39.107.143.46";

        ReturnInfo returnInfo = iSoMruService.login(restWebLoginReq,ip);
        if(returnInfo.getReturnCode()== MessageCode.COMMON_SUCCEED_FLAG){
           RestWebLoginResp restWebLoginResp = (RestWebLoginResp)returnInfo.getReturnData();
            System.out.println(restWebLoginResp);

            //终端列表
//            returnInfo = iSoMruService.getEndpoints(restWebLoginResp.getToken(),ip);
            //添加终端
            RestEndpointReq restEndpointReq = new RestEndpointReq();
            restEndpointReq.setSystemName("陈尚测试");
            restEndpointReq.setCallProtocol(CallProtocol.SVC);
            restEndpointReq.setDeviceSn("888888888");
            restEndpointReq.setEndpointType(EndpointType.M16);
            returnInfo = iSoMruService.addEndpoint(restWebLoginResp.getToken(),ip,restEndpointReq);


        }else{

        }
        return returnInfo;
    }

}
