package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SuperUserGetReqBean
 * @Description 查询客户代表入参
 * @Author Liujt
 * @Date 2020/1/18 15:38
 **/
@Data
@ApiModel(value = "查询客户代表入参")
public class SuperUserGetReqBean {
    @ApiModelProperty(value = "用户名称(模糊匹配)")
    private String user_name;

    @ApiModelProperty(value = "用户ID")
    private Integer user_id;
}
