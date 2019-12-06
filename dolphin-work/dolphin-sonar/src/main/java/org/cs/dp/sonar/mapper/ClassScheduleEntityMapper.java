package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.ClassScheduleEntity;

public interface ClassScheduleEntityMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(ClassScheduleEntity record);

    int insertSelective(ClassScheduleEntity record);

    ClassScheduleEntity selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(ClassScheduleEntity record);

    int updateByPrimaryKey(ClassScheduleEntity record);
}