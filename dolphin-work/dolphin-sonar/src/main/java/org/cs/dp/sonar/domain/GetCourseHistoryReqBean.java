package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCourseHistoryReqBean
 * @Description 查询历史信息入参
 * @Author Liujt
 * @Date 2020/2/14 18:14
 **/
@Data
@ApiModel(value = "查询历史信息入参")
public class GetCourseHistoryReqBean {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "组织id")
    private Integer org_id;

    @ApiModelProperty(value = "租户id", hidden = true)
    private Integer customer_id;
}
