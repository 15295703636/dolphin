package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.CourseHistoryDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseHistoryDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insertByCourseId(@Param(value = "newId") Integer newId, @Param(value = "course_id") Integer course_id);

    int insertSelective(CourseHistoryDeviceEntity record);

    CourseHistoryDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseHistoryDeviceEntity record);
}
