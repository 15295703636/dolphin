package org.cs.dp.sonar.domain.entity;

import java.util.Date;

public class DeviceEntity {
    private Integer device_id;

    private Integer org_id;

    private String device_name;

    private String device_nickName;

    private String device_type;

    private String device_state;

    private String device_serial_number;

    private Date create_time;

    public DeviceEntity(Integer device_id, Integer org_id, String device_name, String device_nickName, String device_type, String device_state, String device_serial_number, Date create_time) {
        this.device_id = device_id;
        this.org_id = org_id;
        this.device_name = device_name;
        this.device_nickName = device_nickName;
        this.device_type = device_type;
        this.device_state = device_state;
        this.device_serial_number = device_serial_number;
        this.create_time = create_time;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public String getDevice_nickName() {
        return device_nickName;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getDevice_state() {
        return device_state;
    }

    public String getdevice_serial_number() {
        return device_serial_number;
    }

    public Date getCreate_time() {
        return create_time;
    }
}