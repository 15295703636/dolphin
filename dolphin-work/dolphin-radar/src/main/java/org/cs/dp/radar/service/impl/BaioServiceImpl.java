package org.cs.dp.radar.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.jsonrpc.JsonRpcReq;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.cs.dp.radar.config.BaioSender;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IMruService;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@Service
public class BaioServiceImpl implements IBaioService {

    @Autowired
    BaioSender baioSender;

    @Autowired
    ISomqClient iSomqClient;

    @Override
    public ReturnInfo sendmsg(String msg, String queue) {
        return baioSender.sendAll(msg,queue);
    }

    @Override
    public ReturnInfo recMsgFromBaio1(String msg) {
        return iSomqClient.receiveMsg(msg);
    }

    @Override
    public ReturnInfo keepalive(String msg) {
        return iSomqClient.keepalive(msg,1);
    }


}
