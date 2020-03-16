package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetUserReqBean
 * @Description
 * @Author Liujt
 * @Date 2020/2/10 10:25
 **/
@Data
@ApiModel(value = "查询用户列表入参")
public class GetUserReqBean {
    @ApiModelProperty(value = "组织Id")
    private Integer org_id;

    @ApiModelProperty(value = "用户名")
    private String user_name;

    @ApiModelProperty(value = "角色")
    private Integer role_id;

    @ApiModelProperty(value = "租户Id", hidden = true)
    private Integer customer_id;

    @ApiModelProperty(value = "根据名称判断出来的角色信息")
    private Integer by_name_role;
}
