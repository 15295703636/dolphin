package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName GetCourseReqBean
 * @Description 查询进行中日程管理
 * @Author Liujt
 * @Date 2019/12/11 9:36
 **/
@Data
@ApiModel(value = "查询进行中日程管理")
public class GetCourseReqBean {

    @ApiModelProperty(value = "组织ID")
    private Integer orgId;

    @ApiModelProperty(value = "输入条件")
    private String content;
}
