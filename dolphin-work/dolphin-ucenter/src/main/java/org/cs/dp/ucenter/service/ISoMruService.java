package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.RestOrgUserReq;
import org.cs.dp.radar.api.entity.RestWebLoginReq;

import java.util.List;

public interface ISoMruService {
    ReturnInfo login(RestWebLoginReq restWebLoginReq, String url);
    ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq);
    ReturnInfo updateUser(String token, String url,
                          String userId, RestOrgUserReq restOrgUserReq);
    ReturnInfo getUsers(String token, String url);
    ReturnInfo getUser(String token, String userId, String url);
    ReturnInfo deleteUser(String token, String url,
                          String userId);
}
