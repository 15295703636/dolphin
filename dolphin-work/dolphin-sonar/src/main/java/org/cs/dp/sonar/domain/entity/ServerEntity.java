package org.cs.dp.sonar.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "服务管理实体")
public class ServerEntity {
    @ApiModelProperty(value = "服务唯一主键")
    private Integer server_id;

    @ApiModelProperty(value = "服务名称")
    private String server_name;

    @ApiModelProperty(value = "服务类型")
    private Integer server_type;

    @ApiModelProperty(value = "服务ip")
    private String server_ip;

    @ApiModelProperty(value = "服务端口")
    private Integer server_port;

    @ApiModelProperty(value = "NATIp")
    private String server_nat_ip;

    @ApiModelProperty(value = "NAT端口")
    private Integer server_nat_port;

    @ApiModelProperty(value = "服务备注")
    private String server_desc;

    @ApiModelProperty(value = "服务ID")
    private String serial_number;

    public ServerEntity() {
    }

    public ServerEntity(Integer server_id, String server_name, Integer server_type, String server_ip, Integer server_port, String server_nat_ip, Integer server_nat_port, String server_desc, String serial_number) {
        this.server_id = server_id;
        this.server_name = server_name;
        this.server_type = server_type;
        this.server_ip = server_ip;
        this.server_port = server_port;
        this.server_nat_ip = server_nat_ip;
        this.server_nat_port = server_nat_port;
        this.server_desc = server_desc;
        this.serial_number = serial_number;
    }
}