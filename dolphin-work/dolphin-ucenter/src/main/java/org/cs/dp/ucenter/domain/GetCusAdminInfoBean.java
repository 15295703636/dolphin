package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCusAdminInfoBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/1/19 11:55
 **/
@Data
@ApiModel(value = "查询租户Admin用户信息")
public class GetCusAdminInfoBean {
    @ApiModelProperty(value = "租户ID")
    private Integer id;
}
