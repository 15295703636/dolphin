package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName UserConditionBean
 * @Description 用户信息查询条件封装
 * @Author Liujt
 * @Date 2019/12/11 9:13
 **/
@Data
@ApiModel(value = "用户关联查询条件和业务条件分开")
public class UserConditionBean {
    private Integer userId = 100;//ThreadLocalUserInfoUtil.get().getUser_id();
    private Integer orgId = 100;//ThreadLocalUserInfoUtil.get().getOrg_id();
    private Integer customerId = 100;//ThreadLocalUserInfoUtil.get().getCustomer_id();
}
