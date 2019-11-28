package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.SHA1Util;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.sonar.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 调用radar服务mru相关接口
 */
@Service
@Slf4j
public class ISoMruServiceImpl implements ISoMruService {
    @Autowired
    IMruClient iMruClient;

    @Override
    public ReturnInfo createUt12(Ut12Entity ut12Entity) {
        return iMruClient.createUt12(ut12Entity);
    }

    @Override
    public ReturnInfo login(RestWebLoginReq restWebLoginReq, String url){
        try{
            restWebLoginReq.setPassword(SHA1Util.sha1(restWebLoginReq.getPassword()));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        ReturnInfo returnInfo = iMruClient.login(restWebLoginReq,url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()== MessageCode.COMMON_SUCCEED_FLAG){
            RestWebLoginResp restWebLoginResp = JSON.parseObject(s,new TypeReference<RestWebLoginResp>(){});
            returnInfo.setReturnData(restWebLoginResp);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,new TypeReference<RestError>(){});
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo getEndpoints(String token, String url) {
        ReturnInfo returnInfo = iMruClient.getEndpoints(token,url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()==MessageCode.COMMON_SUCCEED_FLAG){
            List<RestEndpoint> list = JSON.parseArray(s,RestEndpoint.class);
            returnInfo.setReturnData(list);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo addEndpoint(String token, String url, RestEndpointReq restEndpointReq) {
        ReturnInfo returnInfo = iMruClient.addEndpoint(token, url, restEndpointReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()==MessageCode.COMMON_SUCCEED_FLAG){
            RestEndpoint list = JSON.parseObject(s,RestEndpoint.class);
            returnInfo.setReturnData(list);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo updateEndpoint(String token, String url, String endpointId, RestEndpointReq restEndpointReq) {
        ReturnInfo returnInfo = iMruClient.updateEndpoint(token, url, endpointId, restEndpointReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()==MessageCode.COMMON_SUCCEED_FLAG){
            RestEndpoint list = JSON.parseObject(s,RestEndpoint.class);
            returnInfo.setReturnData(list);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo deleteEndpoint(String token, String url, String endpointId) {
        ReturnInfo returnInfo = iMruClient.deleteEndpoint(token, url, endpointId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()==MessageCode.COMMON_SUCCEED_FLAG){
            RestEndpoint list = JSON.parseObject(s,RestEndpoint.class);
            returnInfo.setReturnData(list);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo getEndpoint(String token, String url, String endpointId) {
        ReturnInfo returnInfo = iMruClient.getEndpoint(token, url, endpointId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if(returnInfo.getReturnCode()==MessageCode.COMMON_SUCCEED_FLAG){
            RestEndpoint list = JSON.parseObject(s,RestEndpoint.class);
            returnInfo.setReturnData(list);
        }else{
            RestError restWebLoginResp = JSON.parseObject(s,RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }
}
