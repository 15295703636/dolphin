package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.SHA1Util;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.ucenter.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 调用radar服务mru相关接口
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class ISoMruServiceImpl implements ISoMruService {
    @Autowired
    IMruClient iMruClient;

    @Override
    public ReturnInfo login(RestWebLoginReq restWebLoginReq, String url) {
        try {
            restWebLoginReq.setPassword(SHA1Util.sha1(restWebLoginReq.getPassword()));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        ReturnInfo returnInfo = iMruClient.login(restWebLoginReq, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestWebLoginResp restWebLoginResp = JSON.parseObject(s, new TypeReference<RestWebLoginResp>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, new TypeReference<RestError>() {
            });
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq) {
        ReturnInfo returnInfo = iMruClient.addUser(token, url, restOrgUserReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestUser list = JSON.parseObject(s, RestUser.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo updateUser(String token, String url, String userId, RestOrgUserReq restOrgUserReq) {
        ReturnInfo returnInfo = iMruClient.updateUser(token, url, userId, restOrgUserReq);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestUser list = JSON.parseObject(s, RestUser.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo getUsers(String token, String url) {
        ReturnInfo returnInfo = iMruClient.getUsers(token, url);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            List<RestUser> list = JSON.parseArray(s, RestUser.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo getUser(String token, String userId, String url) {
        ReturnInfo returnInfo = iMruClient.getUser(token, url, userId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            RestUser list = JSON.parseObject(s, RestUser.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }

    @Override
    public ReturnInfo deleteUser(String token, String url, String userId) {
        ReturnInfo returnInfo = iMruClient.deleteUser(token, url, userId);
        String s = JSON.toJSONString(returnInfo.getReturnData());
        if (returnInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            Map list = JSON.parseObject(s, Map.class);
            returnInfo.setReturnData(list);
        } else {
            RestError restWebLoginResp = JSON.parseObject(s, RestError.class);
            returnInfo.setReturnData(restWebLoginResp);
        }
        return returnInfo;
    }
}
