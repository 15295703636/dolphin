package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.CourseEntity;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer course_id);

    int insert(CourseEntity record);

    int insertSelective(CourseEntity record);

    CourseEntity selectByPrimaryKey(Integer course_id);

    int updateByPrimaryKeySelective(CourseEntity record);

    int updateByPrimaryKey(CourseEntity record);
}
