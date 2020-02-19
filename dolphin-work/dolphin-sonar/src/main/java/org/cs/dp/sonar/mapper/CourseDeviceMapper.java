package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.CourseResBean;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseDeviceEntity record);

    int insertBatch(List<CourseDeviceEntity> param);

    int insertByScheduleId(@Param(value = "courseId") Integer courseId, @Param(value = "scheduleId") Integer scheduleId);

    int insertSelective(CourseDeviceEntity record);

    CourseDeviceEntity selectByPrimaryKey(Integer id);

    List<CourseResBean> selectByCourseId(Integer id);

    int updateByPrimaryKeySelective(CourseDeviceEntity record);

}
