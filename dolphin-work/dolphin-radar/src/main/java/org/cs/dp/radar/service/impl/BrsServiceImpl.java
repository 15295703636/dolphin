package org.cs.dp.radar.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.jsonrpc.JsonRpcReq;
import org.cs.dp.radar.api.entity.brs.BrsMethod;
import org.cs.dp.radar.api.entity.brs.BssTaskReq;
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
    public ReturnInfo keepalive(String msg) {
        return iSomqClient.keepalive(msg,2);
    }

    @Override
    public ReturnInfo recMsgFromBrs2(String msg) {
        return null;//iSomqClient.receiveMsg(msg);
    }

    @Override
    public ReturnInfo login(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Login);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo startTask(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Start_Task);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo stopTask(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Stop_Task);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo queryTask(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Query_Task);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo deleteTaskTask(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Delete_Task);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo uploadFile(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Upload_file);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo uploadThumbnail(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Upload_Thumbnail);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }

    @Override
    public ReturnInfo catThumbnail(BssTaskReq bssTaskReq, String queue) {
        bssTaskReq.setType(BrsMethod.CreateProgram_Method);
        bssTaskReq.setSubtype(BrsMethod.CreateProgram_Method_Subtype_Cut_Thumbnail);
        Object obj = brsSender.sendAll(JSONObject.toJSONString(bssTaskReq),queue);
        return ReturnInfo.ok(obj);
    }


}
