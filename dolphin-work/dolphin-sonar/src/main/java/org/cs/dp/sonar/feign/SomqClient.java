package org.cs.dp.sonar.feign;

import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.jsonrpc.JsonRpcRsp;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * 供radar服务中接收mq的回调
 */
@RestController
public class SomqClient implements ISomqClient {

    @Override
    @GetMapping(API_PREFIX+"/receivemsg")
    public ReturnInfo receiveMsg(@RequestParam String msg) {
        ReturnInfo returnInfo = new ReturnInfo();
        JsonRpcRsp rsp = JsonRpcRsp.generateMsg(msg, HashMap.class);
        //处理消息
//        if( rsp.getError() != null)
//        {
//            returnInfo.setReturnCode(rsp.getError().getCode());
//        }
//        else
//        {
//            returnInfo.setReturnData(rsp.getResult());
//        }
        return returnInfo;
    }
}
