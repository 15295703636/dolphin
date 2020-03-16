package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户实体")
public class UserEntity {
    @ApiModelProperty(value = "ID")
    private Integer user_id;

    @ApiModelProperty(value = "用户登录名")
    private String user_name;

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

    @ApiModelProperty(value = "所属")
    private String user_desc;

    @ApiModelProperty(value = "云视讯UserId")
    private Long ysx_id;

}