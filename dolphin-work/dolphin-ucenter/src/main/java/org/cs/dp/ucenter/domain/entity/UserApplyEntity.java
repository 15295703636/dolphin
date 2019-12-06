package org.cs.dp.ucenter.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "用户申请实体")
public class UserApplyEntity {
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

    @ApiModelProperty(value = "申请结果 0未处理 1通过 2拒绝")
    private Integer apply_result;

    private Date create_time;

    private Date apply_time;

    private String user_desc;

    public UserApplyEntity(Integer user_id, String user_name, String user_qname, String user_pwd, String user_email, Integer role_id, Boolean user_statu, String user_number, String user_tel, String user_code, Integer apply_result, Date create_time, Date apply_time, String user_desc) {
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
        this.apply_result = apply_result;
        this.create_time = create_time;
        this.apply_time = apply_time;
        this.user_desc = user_desc;
    }

}