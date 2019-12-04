package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.common.Constant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName ResetPwdBean
 * @Description 重置密码入参
 * @Author Liujt
 * @Date 2019/11/28 11:37
 **/
@Data
@ApiModel("重置密码入参")
public class ResetPwdBean {

    @ApiModelProperty(value = "用户id", required = true)
    @NotNull(message = Constant.RESET_PWD_ID_MSG)
    private Integer userId;

    @ApiModelProperty(value = "当前密码", required = true)
    @NotBlank(message = Constant.RESET_PWD_OLD_MSG)
    private String oldPwd;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = Constant.RESET_PWD_NEW_MSG)
    private String newPwd;

}
