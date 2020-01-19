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
@ApiModel("更改密码入参")
public class ResetSuperPwdBean {

    @ApiModelProperty(value = "更改密码用户的id", required = true)
    @NotNull(message = Constant.RESET_PWD_ID_MSG)
    private Integer user_id;

    @ApiModelProperty(value = "当前密码", required = true)
    //@NotBlank(message = Constant.RESET_PWD_OLD_MSG)
    private String user_pwd;

    @ApiModelProperty(value = "新密码", required = true)
    @NotBlank(message = Constant.RESET_PWD_NEW_MSG)
    private String new_pwd;

}
