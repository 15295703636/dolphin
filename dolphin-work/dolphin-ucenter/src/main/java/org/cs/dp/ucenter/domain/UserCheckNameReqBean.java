package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName CustomerCheckNameReqBean
 * @Description 用户名称校验
 * @Author Liujt
 * @Date 2020/1/18 17:19
 **/
@Data
@ApiModel(value = "用户名称校验")
public class UserCheckNameReqBean {
    @ApiModelProperty(value = "ID可为空")
    private Integer id;

    @NotBlank(message = "用户名称不能为空")
    @ApiModelProperty(value = "用户名称")
    private String user_name;
}
