package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class MeetingEntity {
    private Integer meeting_id;

    private String meeting_name;

    private String meeting_state;

    private String meeting_date;

    private String meeting_duration;

    private String meeting_number;

    private String device_id;

    private String isLive;

    private String isRecord;

    private String resolving_power;

    private String bandwidth;

    private Integer org_id;

    private String create_time;

    private Integer create_user_id;

    public MeetingEntity() {
    }

    public MeetingEntity(Integer meeting_id, String meeting_name, String meeting_state, String meeting_date, String meeting_duration, String meeting_number, String device_id, String isLive, String isRecord, String resolving_power, String bandwidth, Integer org_id, String create_time, Integer create_user_id) {
        this.meeting_id = meeting_id;
        this.meeting_name = meeting_name;
        this.meeting_state = meeting_state;
        this.meeting_date = meeting_date;
        this.meeting_duration = meeting_duration;
        this.meeting_number = meeting_number;
        this.device_id = device_id;
        this.isLive = isLive;
        this.isRecord = isRecord;
        this.resolving_power = resolving_power;
        this.bandwidth = bandwidth;
        this.org_id = org_id;
        this.create_time = create_time;
        this.create_user_id = create_user_id;
    }
}