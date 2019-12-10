package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class CourseDeviceEntity {
    private Integer id;

    private Integer course_id;

    private Integer device_id;

    public CourseDeviceEntity() {
    }

    public CourseDeviceEntity(Integer course_id, Integer device_id) {
        this.course_id = course_id;
        this.device_id = device_id;
    }

    public CourseDeviceEntity(Integer id, Integer course_id, Integer device_id) {
        this.id = id;
        this.course_id = course_id;
        this.device_id = device_id;
    }

}