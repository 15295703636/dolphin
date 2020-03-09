package org.cs.dp.ucenter.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.RestDeptReq;
import org.cs.dp.radar.api.entity.RestOrgUserReq;
import org.cs.dp.radar.api.entity.RestWebLoginReq;

import java.util.List;

public interface ISoMruService<T> {
    //用户操作
    String ADDUSER_NAME = "addUser";
    String UPDATEUSER_NAME = "updateUser";
    String GETUSERS_NAME = "getUsers";
    String GETUSER_NAME = "getUser";
    String DELETEUSER_NAME = "deleteUser";

    //组织操作
    String ADDDEPT_NAME = "addDept";
    String UPDATEDEPT_NAME = "updateDept";
    String GETADDDEPTS_NAME = "getaddDepts";
    String GETADDDEPT_NAME = "getaddDept";
    String DELETEADDDEPT_NAME = "deleteaddDept";

    ReturnInfo login(RestWebLoginReq restWebLoginReq, String url);

    ReturnInfo addUser(String token, String url, RestOrgUserReq restOrgUserReq);

    ReturnInfo updateUser(String token, String url, String userId, RestOrgUserReq restOrgUserReq);

    ReturnInfo getUsers(String token, String url);

    ReturnInfo getUser(String token, String url, String userId);

    ReturnInfo deleteUser(String token, String url, String userId);

    ReturnInfo getService(String method, Integer customer_id, T obj);

    ReturnInfo addDept(String token, String url, RestDeptReq restDeptReq);

    ReturnInfo updateDept(String token, String url, String deptId, RestDeptReq restDeptReq);

    ReturnInfo getDepts(String token, String url);

    ReturnInfo getDept(String token, String deptId, String url);

    ReturnInfo deleteDept(String token, String url, String deptId);


}
