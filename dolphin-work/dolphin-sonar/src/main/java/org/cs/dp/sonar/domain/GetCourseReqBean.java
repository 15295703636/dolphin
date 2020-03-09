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
    private Integer org_id;

    @ApiModelProperty(value = "类型 1会议 2互动 3录播")
    private String type;

    @ApiModelProperty(value = "租户ID", hidden = true)
    private Integer customer_id;

    @ApiModelProperty(value = "日程名称,主讲姓名，端名称")
    private String name;

    @ApiModelProperty(value = "主讲端Id", hidden = true)
    private Integer local_classroomId;
}
