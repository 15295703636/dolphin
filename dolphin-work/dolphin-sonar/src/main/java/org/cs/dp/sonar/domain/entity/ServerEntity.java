package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class ServerEntity {
    private Integer server_id;

    private String server_name;

    private Integer server_type;

    private String server_ip;

    private Integer server_port;

    private String server_nat_ip;

    private Integer server_nat_port;

    private String server_desc;

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