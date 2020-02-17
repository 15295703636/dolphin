package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.RestOrgUserReq;
import org.cs.dp.radar.api.entity.RestWebLoginReq;

import java.util.List;

public interface ISoMruService<T> {
    String ADDUSER_NAME =  "addUser";
    String UPDATEUSER_NAME =  "updateUser";
    String GETUSERS_NAME =  "getUsers";
    String GETUSER_NAME =  "getUser";
    String DELETEUSER_NAME =  "deleteUser";

    ReturnInfo login(RestWebLoginReq restWebLoginReq, String url);

    ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq);

    ReturnInfo updateUser(String token, String url, String userId, RestOrgUserReq restOrgUserReq);

    ReturnInfo getUsers(String token, String url);

    ReturnInfo getUser(String token, String url, String userId);

    ReturnInfo deleteUser(String token, String url, String userId);

    ReturnInfo getService(String method, T obj);
}
