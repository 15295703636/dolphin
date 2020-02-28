package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.radar.api.entity.RestPartyLayout;

/**
 * @ClassName RestPartyLayoutReqBean
 * @Description 设置分屏参数封装
 * @Author Liujt
 * @Date 2020/2/27 13:13
 **/
@Data
@ApiModel(value = "设置分屏参数封装")
public class RestPartyLayoutReqBean extends RestPartyLayout {
    
    @ApiModelProperty(value = "云视讯会议ID")
    private String ysx_course_id;

    @ApiModelProperty(value = "云视讯设备ID")
    private String ysx_device_id;
}
