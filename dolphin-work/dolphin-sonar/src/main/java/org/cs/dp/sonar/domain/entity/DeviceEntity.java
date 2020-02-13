package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "设备-端管理")
public class DeviceEntity {
    @ApiModelProperty(value = "")
    private Integer device_id;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "呼叫协议")
    private Integer call_protocol;

    @ApiModelProperty(value = "呼叫号")
    private String call_num;

    @ApiModelProperty(value = "sip号码")
    private String sip;

    @ApiModelProperty(value = "IP地址")
    private String ip;

    @ApiModelProperty(value = "e164号")
    private String e_code;

    @ApiModelProperty(value = "设备类型")
    private Integer device_type;

    @ApiModelProperty(value = "设备状态")
    private Integer device_state;

    @ApiModelProperty(value = "设备序列号")
    private String device_serial_number;

    @ApiModelProperty(value = "添加时间")
    private Date create_time;

    @ApiModelProperty(value = "创建人ID")
    private Integer create_user_id;

}