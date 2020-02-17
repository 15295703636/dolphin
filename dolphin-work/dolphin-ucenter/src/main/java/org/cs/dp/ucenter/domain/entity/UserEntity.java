package org.cs.dp.ucenter.domain.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户实体")
public class UserEntity {
    @ApiModelProperty(value = "ID")
    private Integer user_id;

    @ExcelProperty(index = 0)
    @NotBlank(message = Constant.USER_NAME_MSG)
    @ApiModelProperty(value = "用户登录名")
    private String user_name;

    @ExcelProperty(index = 1)
    @ApiModelProperty(value = "用户名称")
    private String user_qname;

    @ExcelProperty(index = 4)
    @NotBlank(message = Constant.PWD_MSG)
    @ApiModelProperty(value = "用户密码")
    private String user_pwd;

    @Email
    @ExcelProperty(index = 6)
    @ApiModelProperty(value = "用户邮箱")
    private String user_email;

    @ExcelProperty(index = 2)
    @ApiModelProperty(value = "权限类别ID")
    private Integer role_id;

    @ApiModelProperty(value = "用户状态")
    private Boolean user_statu;

    @ApiModelProperty(value = "")
    private String user_number;

    @ExcelProperty(index = 5)
    @ApiModelProperty(value = "用户手机号")
    private String user_tel;

    @ExcelProperty(index = 7)
    @Length(min = 15, max = 18, message = Constant.COMMON_ID_CARD)
    @ApiModelProperty(value = "身份证号")
    private String user_code;

    @ApiModelProperty(value = "所属")
    private String user_desc;

    @ApiModelProperty(value = "云视讯UserId")
    private Long ysx_id;

}