package org.cs.dolphin.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserInfo {
    @ApiModelProperty(value = "ID")
    private Integer user_id;

    @ApiModelProperty(value = "用户登录名")
    private String user_name;

    @ApiModelProperty(value = "用户名称")
    private String user_qname;

    @ApiModelProperty(value = "用户密码")
    private String user_pwd;

    @ApiModelProperty(value = "用户邮箱")
    private String user_email;

    @ApiModelProperty(value = "权限类别ID")
    private Integer role_id;

    @ApiModelProperty(value = "用户状态")
    private Boolean user_statu;

    @ApiModelProperty(value = "")
    private String user_number;

    @ApiModelProperty(value = "用户手机号")
    private String user_tel;

    @ApiModelProperty(value = "身份证号")
    private String user_code;

    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "")
    private String user_desc;

    @ApiModelProperty(value = "组织类型")
    private String org_type;

    @ApiModelProperty(value = "组织上级类型")
    private String org_preid;


}