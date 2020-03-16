package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CheckAddInfoReqBean
 * @Description
 * @Author Liujt
 * @Date 2020/1/18 9:42
 **/
@Data
@ApiModel(value = "校验用户名手机号入参")
public class CheckAddInfoReqBean {

    @ApiModelProperty(value = "用户名称")
    private String user_name;

    @ApiModelProperty(value = "用户手机号")
    private String user_tel;

    @ApiModelProperty(value = "用户手机号")
    private Integer user_id;

    @ApiModelProperty(value = "用户邮箱")
    private String user_email;
}
