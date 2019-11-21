package org.cs.dolphin.common.base;

import lombok.Data;

@Data
public class UserInfo {
    private Integer userId;

    private String userName;

    private String userQname;

    private String userPwd;

    private String userEmail;

    private Integer roleId;

    private Boolean userStatu;

    private String userNumber;

    private String userTel;

    private String userCode;

    private String userDesc;

}