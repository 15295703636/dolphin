package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName AddCustomerBean
 * @Description 添加租户信息入参
 * @Author Liujt
 * @Date 2019/12/5 18:35
 **/
@Data
@ApiModel(value = "租户信息入参")
public class AddCustomerBean {

    @ApiModelProperty(value = "ID",hidden = true)
    private Integer id;

    @NotBlank(message = Constant.CUSTOMER_NAME_ISEMPTY_MSG)
    @ApiModelProperty(value = "租户名称")
    private String customer_name;

    @NotBlank(message = Constant.CUSTOMER_OUT_NAME)
    @ApiModelProperty(value = "云视讯登录名")
    private String out_name;

    @NotBlank(message = Constant.CUSTOMER_OUT_PWD)
    @ApiModelProperty(value = "云视讯密码")
    private String out_pwd;

}
