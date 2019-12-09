package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class InteractionEntity {
    private Integer interaction_id;

    private String interaction_name;

    private String teacher;

    private String interaction_state;

    private String interaction_date;

    private String interaction_duration;

    private String device_id;

    private String isLive;

    private String isRecord;

    private String resolving_power;

    private String bandwidth;

    private Integer org_id;

    private String create_time;

    private Integer create_user_id;

    public InteractionEntity() {
    }

    public InteractionEntity(Integer interaction_id, String interaction_name, String teacher, String interaction_state, String interaction_date, String interaction_duration, String device_id, String isLive, String isRecord, String resolving_power, String bandwidth, Integer org_id, String create_time, Integer create_user_id) {
        this.interaction_id = interaction_id;
        this.interaction_name = interaction_name;
        this.teacher = teacher;
        this.interaction_state = interaction_state;
        this.interaction_date = interaction_date;
        this.interaction_duration = interaction_duration;
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