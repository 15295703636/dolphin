package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CourseGetByIdResBean
 * @Description 根据本进行中的日程id查询设备信息，日程信息返回
 * @Author Liujt
 * @Date 2020/2/19 16:30
 **/
@Data
@ApiModel(value = "根据本进行中的日程id查询设备信息，日程信息返回")
public class CourseGetByIdResBean {

    @ApiModelProperty(value = "")
    private Integer course_id;
    private String course_type;
    private String course_name;
    private Long ysx_id;

    @ApiModelProperty(value = "直播标志，1直播 0未直播")
    private String isLive;
    @ApiModelProperty(value = "流媒体任务ID", hidden = true)
    private String stream_id;
    @ApiModelProperty(value = "流媒体服务地址", hidden = true)
    private String stream_url;

    @ApiModelProperty(value = "直播地址")
    private String live_url;

    @ApiModelProperty(value = "开始时间")
    private String start_time;

    @ApiModelProperty(value = "时长")
    private String duration;

    @ApiModelProperty(value = "带宽")
    private Integer bandwidth;

    @ApiModelProperty(value = "分辨率")
    private Integer resolving_power;

    @ApiModelProperty(value = "直播观看密码")
    private String live_password;

    private List<CourseResBean> remote;

    private CourseResBean main;

    @ApiModelProperty(value = "互动课专用标志 1上课 2讨论 3发言")
    private Integer class_state;
}
