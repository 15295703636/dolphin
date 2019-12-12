package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "租户-服务管理实体")
public class CustomerServerEntity {

    @ApiModelProperty(value = "服务唯一主键")
    private Integer server_id;

    @ApiModelProperty(value = "服务名称")
    private String server_name;

    @ApiModelProperty(value = "服务ip")
    private String server_ip;

    @ApiModelProperty(value = "服务端口")
    private Integer server_port;

    @ApiModelProperty(value = "NATIp")
    private String server_nat_ip;

    @ApiModelProperty(value = "NAT端口")
    private Integer server_nat_port;

    @ApiModelProperty(value = "序列号")
    private String serial_number;

    @ApiModelProperty(value = "组织ID")
    private Integer org_id;

    @ApiModelProperty(value = "租户ID")
    private Integer customer_id;

}