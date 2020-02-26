package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CourseDeviceAddReqBean
 * @Description 添加日程-端管理入参
 * @Author Liujt
 * @Date 2020/2/25 11:41
 **/
@Data
@ApiModel(value = "添加日程-端管理入参")
public class CourseDeviceAddReqBean {
    @ApiModelProperty(value = "会议id")
    private Integer course_id;

    @ApiModelProperty(value = "云视讯会议id")
    private Long ysx_id;

    @ApiModelProperty(value = "端Id")
    private List<Integer> device_ids;

    @ApiModelProperty(value = "用户Id")
    private List<Integer> user_ids;

}
