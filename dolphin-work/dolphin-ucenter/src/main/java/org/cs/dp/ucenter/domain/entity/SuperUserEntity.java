package org.cs.dp.ucenter.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@ApiModel(value = "平台管理员实体")
public class SuperUserEntity {
    @ApiModelProperty(value = "ID")
    private Integer user_id;

    @ExcelProperty(index = 1)
    @NotBlank(message = Constant.USER_NAME_MSG)
    @ApiModelProperty(value = "用户登录名")
    private String user_name;

    @ExcelProperty(index = 0)
    @ApiModelProperty(value = "用户名称")
    private String user_qname;

    @ExcelProperty(index = 2)
    @NotBlank(message = Constant.PWD_MSG)
    @ApiModelProperty(value = "用户密码")
    private String user_pwd;

    @ApiModelProperty(value = "用户新密码")
    private String new_pwd;

    @ExcelProperty(index = 3)
    @ApiModelProperty(value = "用户邮箱")
    private String user_email;

    @ApiModelProperty(value = "权限类别ID")
    private Integer role_id;

    @ApiModelProperty(value = "用户状态")
    private Boolean user_statu =true;

    @ApiModelProperty(value = "")
    private String user_number;

    @ExcelProperty(index = 4)
    @ApiModelProperty(value = "用户手机号")
    private String user_tel;

    @ApiModelProperty(value = "身份证号")
    private String user_code;

    @ApiModelProperty(value = "用户类型1客户代表；2超级管理员")
    private Integer user_type = 1;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    public SuperUserEntity() {
    }

    public SuperUserEntity(Integer user_id, String user_name, String user_qname,
                           String user_pwd, String user_email, Integer role_id,
                           Boolean user_statu, String user_number, String user_tel,
                           String user_code, Integer user_type, Date create_time) {
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
        this.user_type = user_type;
        this.create_time = create_time;
    }
}