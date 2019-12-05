package org.cs.dp.ucenter.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.domain.entity.UserEntity;

/**
 * @ClassName AddCustomerBean
 * @Description 添加租户信息入参
 * @Author Liujt
 * @Date 2019/12/5 18:35
 **/
@Data
@ApiModel(value = "租户信息入参")
public class AddCustomerBean {

    @ApiModelProperty(value = "租户信息")
    private CustomerEntity customer;

    @ApiModelProperty(value = "租户Admin账号信息")
    private UserEntity user;

}
