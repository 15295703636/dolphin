package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName CourseDeviceDelReqBean
 * @Description 删除端管理入参
 * @Author Liujt
 * @Date 2020/2/25 16:05
 **/
@Data
@ApiModel(value = "会议端操作入参")
public class CourseDeviceReqBean {

    @ApiModelProperty(value = "云视讯会议id")
    private Long course_ysx_id;

    @ApiModelProperty(value = "进行中日程-端ID")
    private Integer id;

    @ApiModelProperty(value = "进行中日程-端ID")
    private Integer device_id;

    @ApiModelProperty(value = "进行中日程-端ID",hidden = true)
    private Long ysx_device_id;

    @ApiModelProperty(value = "挂断连接标志 true连接，false挂断")
    private boolean connect;

    public CourseDeviceReqBean() {
    }

    public CourseDeviceReqBean(Long course_ysx_id, Integer id, Integer device_id) {
        this.course_ysx_id = course_ysx_id;
        this.id = id;
        this.device_id = device_id;
    }
}
