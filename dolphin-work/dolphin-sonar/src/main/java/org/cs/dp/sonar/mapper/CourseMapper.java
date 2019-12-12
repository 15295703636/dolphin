package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.UserConditionBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer course_id);

    int insertSelective(CourseEntity record);

    List<CourseEntity> selectByCondition(@Param(value = "reqCon")GetCourseReqBean getCourseReqBean, @Param(value = "userCon") UserConditionBean user);

    int updateByPrimaryKeySelective(CourseEntity record);

}
