package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName AddCustomerUserBean
 * @Description 添加租户Admin账户
 * @Author Liujt
 * @Date 2019/12/9 10:59
 **/
@Data
public class AddCustomerUserBean {

    @NotBlank(message = Constant.USER_NAME_MSG)
    @ApiModelProperty(value = "用户登录名")
    private String user_name;

    @NotBlank(message = Constant.PWD_MSG)
    @ApiModelProperty(value = "用户密码")
    private String user_pwd;

    @NotBlank(message = Constant.EMAIL_EMPTY_MSG)
    @ApiModelProperty(value = "邮箱")
    private String user_email;

    @NotBlank(message = Constant.TEL_EMPTY_MSG)
    @ApiModelProperty(value = "用户手机号")
    private String user_tel;

    @ApiModelProperty(value = "组织ID", hidden = true)
    private Integer org_id;

    @NotNull(message = Constant.CUSTOMER_ID_ISEMPTY_MSG)
    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;
}
