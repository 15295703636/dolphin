package org.cs.dp.sonar.domain.entity;

import lombok.Data;

@Data
public class CourseHistoryDeviceEntity {
    private Integer id;

    private Integer course_id;

    private Integer device_id;

    private Integer type;

    private Integer state;

    private Integer device_type;

}