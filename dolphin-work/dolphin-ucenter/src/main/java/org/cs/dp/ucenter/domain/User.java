package org.cs.dp.ucenter.domain;


public class User {
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

    public User(Integer userId, String userName, String userQname, String userPwd, String userEmail, Integer roleId, Boolean userStatu, String userNumber, String userTel, String userCode, String userDesc) {
        this.userId = userId;
        this.userName = userName;
        this.userQname = userQname;
        this.userPwd = userPwd;
        this.userEmail = userEmail;
        this.roleId = roleId;
        this.userStatu = userStatu;
        this.userNumber = userNumber;
        this.userTel = userTel;
        this.userCode = userCode;
        this.userDesc = userDesc;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserQname() {
        return userQname;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Boolean getUserStatu() {
        return userStatu;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public String getUserTel() {
        return userTel;
    }

    public String getUserCode() {
        return userCode;
    }

    public String getUserDesc() {
        return userDesc;
    }
}