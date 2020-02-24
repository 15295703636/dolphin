package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCustomerByManIdReqBean
 * @Description TODO
 * @Author Liujt
 * @Date 2020/1/19 10:00
 **/
@Data
@ApiModel(value = "客户代表Id查询租户管理")
public class GetCustomerByManIdReqBean {
    @ApiModelProperty(value = "客户代表userId")
    private Integer manger_id;
}
