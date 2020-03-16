package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CourseDeviceSpeakReqBean
 * @Description 设置发言
 * @Author Liujt
 * @Date 2020/2/28 10:59
 **/
@Data
@ApiModel(value = "设置发言")
public class CourseDeviceSpeakReqBean {

    @ApiModelProperty(value = "标志 true发言 false取消发言  true:开启讨论 false：取消讨论")
    private boolean sign;

    @ApiModelProperty(value = "云视讯会议ID")
    private String ysx_course_id;

    @ApiModelProperty(value = "云视讯主讲端设备ID")
    private String ysx_device_id;

    @ApiModelProperty(value = "云视讯远程端设备ID")
    private List<Long> ysx_device_ids;

    @ApiModelProperty(value = "云视讯发言人设备ID")
    private Long ysx_speak_device_id;

    @ApiModelProperty(value = "是否开启直播 1开启 0未开启")
    private String isLive;
}
