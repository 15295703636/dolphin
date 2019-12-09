package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class MeetingDeviceEntity {
    private Integer id;

    private Integer meeting_id;

    private Integer device_id;

    public MeetingDeviceEntity() {
    }

    public MeetingDeviceEntity(Integer meeting_id, Integer device_id) {
        this.meeting_id = meeting_id;
        this.device_id = device_id;
    }

    public MeetingDeviceEntity(Integer id, Integer meeting_id, Integer device_id) {
        this.id = id;
        this.meeting_id = meeting_id;
        this.device_id = device_id;
    }

}