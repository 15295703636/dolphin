package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class MeetingUserEntity {
    private Integer id;

    private Integer meeting_id;

    private Integer user_id;

    public MeetingUserEntity() {
    }

    public MeetingUserEntity(Integer meeting_id, Integer user_id) {
        this.meeting_id = meeting_id;
        this.user_id = user_id;
    }

    public MeetingUserEntity(Integer id, Integer meeting_id, Integer user_id) {
        this.id = id;
        this.meeting_id = meeting_id;
        this.user_id = user_id;
    }

}