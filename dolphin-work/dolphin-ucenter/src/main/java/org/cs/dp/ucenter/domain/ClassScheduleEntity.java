package org.cs.dp.ucenter.domain;

import lombok.Data;

@Data
public class ClassScheduleEntity{
    private Integer scheduleId;

    private String scheduleName;

    private Integer localClassId;

    private String localTeacher;

    private String grade;

    private String subject;

    private Integer students;

    private String assistTeacher;

    private String remoteClassIds;

    private String startTime;

    private String endTime;

    private Integer lesson_seq;

    private String datetime;

    private Integer weekday;

    private String channel_id;

    private String course_type;

    private Integer base_id;

    private String isRecord;

    private String isLive;

    private String isPublish;

    private String isComment;

    private String isDual;

    private String state;

    private Integer course_id;

    private Integer media_id;

    public ClassScheduleEntity(Integer scheduleId, String scheduleName, Integer localClassId, String localTeacher, String grade, String subject, Integer students, String assistTeacher, String remoteClassIds, String startTime, String endTime, Integer lesson_seq, String datetime, Integer weekday, String channel_id, String course_type, Integer base_id, String isRecord, String isLive, String isPublish, String isComment, String isDual, String state, Integer course_id, Integer media_id) {
        this.scheduleId = scheduleId;
        this.scheduleName = scheduleName;
        this.localClassId = localClassId;
        this.localTeacher = localTeacher;
        this.grade = grade;
        this.subject = subject;
        this.students = students;
        this.assistTeacher = assistTeacher;
        this.remoteClassIds = remoteClassIds;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lesson_seq = lesson_seq;
        this.datetime = datetime;
        this.weekday = weekday;
        this.channel_id = channel_id;
        this.course_type = course_type;
        this.base_id = base_id;
        this.isRecord = isRecord;
        this.isLive = isLive;
        this.isPublish = isPublish;
        this.isComment = isComment;
        this.isDual = isDual;
        this.state = state;
        this.course_id = course_id;
        this.media_id = media_id;
    }
}