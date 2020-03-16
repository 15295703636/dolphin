package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CustomerGetByNameAndStateReqBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/1/18 16:11
 **/
@Data
@ApiModel(value = "根据租户名称状态查询列表入参")
public class CustomerByNameAndStateReqBean {

    @ApiModelProperty(value = "租户名称")
    private String customer_name;

    @ApiModelProperty(value = "租户状态:1启用2停用")
    private String customer_status;
}
