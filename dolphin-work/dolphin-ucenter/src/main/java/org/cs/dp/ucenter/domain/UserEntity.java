package org.cs.dp.ucenter.domain;

import lombok.Data;

@Data
public class UserEntity {
    private Integer user_id;

    private String user_name;

    private String user_qname;

    private String user_pwd;

    private String user_email;

    private Integer role_id;

    private Boolean user_statu;

    private String user_number;

    private String user_tel;

    private String user_code;

    private String user_desc;

    public UserEntity(Integer user_id, String user_name, String user_qname, String user_pwd, String user_email, Integer role_id, Boolean user_statu, String user_number, String user_tel, String user_code, String user_desc) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_qname = user_qname;
        this.user_pwd = user_pwd;
        this.user_email = user_email;
        this.role_id = role_id;
        this.user_statu = user_statu;
        this.user_number = user_number;
        this.user_tel = user_tel;
        this.user_code = user_code;
        this.user_desc = user_desc;
    }
}