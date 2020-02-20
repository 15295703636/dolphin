package org.cs.dp.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.SHA1Util;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.radar.api.entity.*;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.sonar.api.feign.IServerClient;
import org.cs.dp.ucenter.common.RedisUtil;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.mapper.CustomerMapper;
import org.cs.dp.ucenter.mapper.UserMapper;
import org.cs.dp.ucenter.service.ISoMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IServerClient iServerClient;

    @Override
    public ReturnInfo getService(String method, Integer customer_id, Object obj) {
        log.info("云视讯：入参：{}——{}", method, JSONObject.toJSONString(obj));
        ReturnInfo returnInfo = null;
        RestWebLoginReq restWebLoginReq = new RestWebLoginReq();

        String url = null;

        Map reqPamra = new HashMap();
        reqPamra.put("type", 15);
        ReturnInfo serverInfo = iServerClient.getServer(reqPamra);
        if (serverInfo.getReturnCode() == MessageCode.COMMON_SUCCEED_FLAG) {
            url = (String) JSONArray.parseArray(JSONObject.toJSONString(serverInfo.getReturnData()), Map.class).get(0).get("server_ip");
        }
        UserInfo userInfo = ThreadLocalUserInfoUtil.get();
        //如果当前用户信息为空，说明是添加租户时添加的系统管理员，用维护的云视讯用户，密码
        if (null != userInfo.getUser_type()) {
            CustomerEntity customer = customerMapper.selectByPrimaryKey(customer_id);
            restWebLoginReq.setAccount(customer.getOut_name());
            restWebLoginReq.setPassword(customer.getOut_pwd());
        } else {
            restWebLoginReq.setAccount(userInfo.getUser_name());
            restWebLoginReq.setPassword(
                    userMapper.selectByUserName(userInfo.getUser_name()).getUser_pwd()
            );
        }
        restWebLoginReq.setIntranet("true");

        returnInfo = login(restWebLoginReq, url);

        RestWebLoginResp restWebLoginResp = (RestWebLoginResp) returnInfo.getReturnData();
        String token = restWebLoginResp.getToken();

        if (ADDUSER_NAME.equals(method)) {
            returnInfo = addUser(token, url, (RestOrgUserReq) obj);
        } else if (UPDATEUSER_NAME.equals(method)) {
            returnInfo = updateUser(token, url, "userId", new RestOrgUserReq());
        } else if (GETUSERS_NAME.equals(method)) {
            returnInfo = getUsers(token, url);
        } else if (GETUSER_NAME.equals(method)) {
            returnInfo = getUser(token, url, "userId");
        } else if (DELETEUSER_NAME.equals(method)) {
            returnInfo = deleteUser(token, url, "userId");
        }
        log.info("云视讯：返回：{}——{}", method, JSONObject.toJSONString(returnInfo));
        return returnInfo;
    }

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
    public ReturnInfo getUser(String token, String url, String userId) {
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
