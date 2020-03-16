package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseDeviceSetLecturerReqBean
 * @Description 设置主讲端入参
 * @Author Liujt
 * @Date 2020/2/26 15:59
 **/
@Data
@ApiModel(value = "设置主讲端入参")
public class CourseDeviceSetLecturerReqBean {

    @ApiModelProperty(value = "进行中日程端id")
    private Integer id;

    @ApiModelProperty(value = "平台会议id")
    private Integer course_id;

    @ApiModelProperty(value = "云视讯会议id")
    private String ysx_course_id;

    @ApiModelProperty(value = "设置主讲的云视讯端id")
    private String ysx_device_id;
}
