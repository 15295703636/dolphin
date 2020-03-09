package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseEndReqBean
 * @Description 进行中的会议结束
 * @Author Liujt
 * @Date 2020/2/25 9:42
 **/
@Data
public class CourseEndReqBean {
    @ApiModelProperty(value = "进行中会议id")
    private Integer course_id;
    @ApiModelProperty(value = "云视讯会议id")
    private Long ysx_id;

    @ApiModelProperty(value = "组织Id",hidden = true)
    private Integer org_id;

    @ApiModelProperty(value = "租户ID",hidden = true)
    private Integer customer_id;
}
