package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseDeviceMuteReqBean
 * @Description 设备静音控制入参
 * @Author Liujt
 * @Date 2020/2/26 10:45
 **/
@Data
@ApiModel(value = "设备静音控制入参")
public class CourseDeviceMuteReqBean {

    @ApiModelProperty(value = "静音标志 true静音 false解除静音")
    private boolean muteAudio;

    @ApiModelProperty(value = "云视讯会议id")
    private String ysx_course_id;

    @ApiModelProperty(value = "要静音的端id,存在值单个静音，没有值全部静音")
    private String ysx_device_id;

}
