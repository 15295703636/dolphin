package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.CourseGetByIdResBean;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;

import java.util.List;

@Mapper
public interface CourseMapper {
    int deleteByPrimaryKey(Integer course_id);

    int updateClassState(@Param(value = "state") Integer state, @Param(value = "ysx_id") Integer ysx_id);

    int updateLiveState(@Param(value = "url") String url,
                        @Param(value = "uuid") String uuid,
                        @Param(value = "ysx_id") Integer ysx_id);

    int insertSelective(CourseEntity record);

    List<CourseEntity> selectByCondition(@Param(value = "reqCon") GetCourseReqBean getCourseReqBean);

    CourseGetByIdResBean selectById(Integer id);

    CourseEntity selectByIdResAll(Integer id);

    CourseEntity selectByIdYsxId(Long ysx_id);

    int updateByPrimaryKeySelective(CourseEntity record);

}
