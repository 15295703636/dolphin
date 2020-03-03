package org.cs.dp.sonar.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName LiveStreamingReqBean
 * @Description 直播设置入参
 * @Author Liujt
 * @Date 2020/3/3 16:07
 **/
@Data
@ApiModel(value = "直播设置入参")
public class LiveStreamingReqBean {
    @ApiModelProperty(value = "设置标志 true开启直播  false关闭直播")
    private boolean sign;

    @ApiModelProperty(value = "云视讯")
    private String ysx_course_id;
}
