package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.sonar.domain.entity.ClassScheduleEntity;

/**
 * 课程表
 */
@Mapper
public interface ClassScheduleMapper {
    int deleteByPrimaryKey(Integer scheduleId);

    int insert(ClassScheduleEntity record);

    int insertSelective(ClassScheduleEntity record);

    ClassScheduleEntity selectByPrimaryKey(Integer scheduleId);

    int updateByPrimaryKeySelective(ClassScheduleEntity record);

    int updateByPrimaryKey(ClassScheduleEntity record);
}