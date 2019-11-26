package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName OrgIdAndTokenBean
 * @Description 组织id和token
 * @Author Liujt
 * @Date 2019/11/26 17:02
 **/
@Data
@ApiModel(value = "组织id和token")
public class OrgIdAndTokenBean {
    @ApiModelProperty(value = "组织id")
    private Integer org_id;
    @ApiModelProperty(value = "token")
    private String token;
}
