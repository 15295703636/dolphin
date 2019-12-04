package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "设备实体")
public class DeviceEntity {

    @ApiModelProperty(value = "ID")
    private Integer device_id;

    @ApiModelProperty(value = "设备名称")
    private String device_name;

    @ApiModelProperty(value = "设备昵称")
    private String device_nickName;

    @ApiModelProperty(value = "设备类型")
    private String device_type;

    @ApiModelProperty(value = "设备状态")
    private String device_state;

    @ApiModelProperty(value = "设备mac")
    private String device_mac;

    @ApiModelProperty(value = "设备uri")
    private String device_uri;

    public DeviceEntity(Integer device_id, String device_name, String device_nickName, String device_type, String device_state, String device_mac, String device_uri) {
        this.device_id = device_id;
        this.device_name = device_name;
        this.device_nickName = device_nickName;
        this.device_type = device_type;
        this.device_state = device_state;
        this.device_mac = device_mac;
        this.device_uri = device_uri;
    }

}