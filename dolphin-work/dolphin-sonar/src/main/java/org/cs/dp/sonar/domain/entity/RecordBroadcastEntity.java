package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class RecordBroadcastEntity {
    private Integer rb_id;

    private String rb_name;

    private String teacher;

    private Integer rb_state;

    private String rb_date;

    private String rb_duration;

    private String device_id;

    private String isLive;

    private String isRecord;

    private Integer org_id;

    private String create_time;

    private Integer create_user_id;

    public RecordBroadcastEntity(Integer rb_id, String rb_name, String teacher, Integer rb_state, String rb_date, String rb_duration, String device_id, String isLive, String isRecord, Integer org_id, String create_time, Integer create_user_id) {
        this.rb_id = rb_id;
        this.rb_name = rb_name;
        this.teacher = teacher;
        this.rb_state = rb_state;
        this.rb_date = rb_date;
        this.rb_duration = rb_duration;
        this.device_id = device_id;
        this.isLive = isLive;
        this.isRecord = isRecord;
        this.org_id = org_id;
        this.create_time = create_time;
        this.create_user_id = create_user_id;
    }

}