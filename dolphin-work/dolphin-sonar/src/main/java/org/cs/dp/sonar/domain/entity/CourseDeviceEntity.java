package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "日程用到的端实体")
public class CourseDeviceEntity {
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "日程ID")
    private Integer course_id;

    @ApiModelProperty(value = "端ID")
    private Integer device_id;

    @ApiModelProperty(value = "类型 1主端  2次端 3旁观")
    private Integer type;

    @ApiModelProperty(value = "状态")
    private Integer state;

    public CourseDeviceEntity() {
    }

    public CourseDeviceEntity(Integer course_id, Integer device_id) {
        this.course_id = course_id;
        this.device_id = device_id;
    }
}