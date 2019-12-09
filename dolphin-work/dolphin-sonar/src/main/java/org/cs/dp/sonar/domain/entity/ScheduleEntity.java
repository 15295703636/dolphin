package org.cs.dp.sonar.domain.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleEntity {
    private Integer id;

    private String name;

    private Integer state;

    private String date;

    private String duration;

    private Integer user_number;

    private String user_ids;

    private String device_id;

    private String device_ids;

    private Integer device_number;

    private Integer type;

    private String isLive;

    private String isRecord;

    private String resolving_power;

    private String bandwidth;

    private Integer org_id;

    private Date create_time;

    private Date update_time;

    private Integer create_user_id;

    public ScheduleEntity() {
    }

    public ScheduleEntity(Integer id, String name, Integer state, String date, String duration, Integer user_number, String user_ids, String device_id, String device_ids, Integer device_number, Integer type, String isLive, String isRecord, String resolving_power, String bandwidth, Integer org_id, Date create_time, Date update_time, Integer create_user_id) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.date = date;
        this.duration = duration;
        this.user_number = user_number;
        this.user_ids = user_ids;
        this.device_id = device_id;
        this.device_ids = device_ids;
        this.device_number = device_number;
        this.type = type;
        this.isLive = isLive;
        this.isRecord = isRecord;
        this.resolving_power = resolving_power;
        this.bandwidth = bandwidth;
        this.org_id = org_id;
        this.create_time = create_time;
        this.update_time = update_time;
        this.create_user_id = create_user_id;
    }

}