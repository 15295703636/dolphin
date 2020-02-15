package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="点播实体管理")
public class VideoDemandEntity {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "主讲人")
    private String teacher_name;

    @ApiModelProperty(value = "日程类型")
    private Integer course_type;

    @ApiModelProperty(value = "创建时间")
    private Date create_time;

    @ApiModelProperty(value = "创建人")
    private Integer create_user;

    @ApiModelProperty(value = "时长(毫秒)")
    private Integer duration;

    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;

}