package org.cs.dp.sonar.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.cs.dp.radar.api.entity.RestPartyLayout;

import java.util.List;

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

    public RestPartyLayoutReqBean() {
    }

    public RestPartyLayoutReqBean(String ysx_course_id, String ysx_device_id) {
        this.ysx_course_id = ysx_course_id;
        this.ysx_device_id = ysx_device_id;
    }

    public RestPartyLayoutReqBean(Object ysx_course_id, Object ysx_device_id, String layoutType, List<Long> deviceIds) {
        if (ysx_course_id instanceof Long) {
            this.ysx_course_id = String.valueOf(ysx_course_id);
        } else {
            this.ysx_course_id = (String) ysx_course_id;
        }
        if (ysx_device_id instanceof Long) {
            this.ysx_device_id = String.valueOf(ysx_device_id);
        } else {
            this.ysx_course_id = (String) ysx_device_id;
        }
        super.setLayoutType(layoutType);
        super.setDeviceIds(deviceIds);
    }
}
