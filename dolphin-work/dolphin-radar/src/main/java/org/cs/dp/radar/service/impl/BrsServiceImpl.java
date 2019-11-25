package org.cs.dp.radar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.jsonrpc.JsonRpcReq;
import org.cs.dp.radar.config.BaioSender;
import org.cs.dp.radar.config.BrsSender;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IBrsService;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BrsServiceImpl implements IBrsService {

    @Autowired
    BrsSender brsSender;

    @Autowired
    ISomqClient iSomqClient;

    @Override
    public ReturnInfo sendmsg(String msg,String method) {
        JsonRpcReq jsonRpcReq = new JsonRpcReq();
        jsonRpcReq.setMethod(method);
        jsonRpcReq.setParams(msg);
        String json = JsonRpcReq.generateJsonStr(jsonRpcReq);
        brsSender.sendAll(json);
        return null;
    }

    @Override
    public ReturnInfo sendmsgtopic(String msg, String method, String queue) {
        JsonRpcReq jsonRpcReq = new JsonRpcReq();
        jsonRpcReq.setMethod(method);
        jsonRpcReq.setParams(msg);
        String json = JsonRpcReq.generateJsonStr(jsonRpcReq);
        Object obj = brsSender.sendAll(json,queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo recMsgFromBrs1(String msg) {
        return iSomqClient.receiveMsg(msg);
    }

    @Override
    public ReturnInfo recMsgFromBrs2(String msg) {
        return iSomqClient.receiveMsg(msg);
    }


}
