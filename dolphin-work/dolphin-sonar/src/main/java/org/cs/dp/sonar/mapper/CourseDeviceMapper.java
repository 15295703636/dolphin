package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.CourseResBean;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByCourseId(Integer id);

    int insert(CourseDeviceEntity record);

    int insertBatch(List<CourseDeviceEntity> param);

    int insertByScheduleId(@Param(value = "courseId") Integer courseId, @Param(value = "scheduleId") Integer scheduleId);

    int insertByHistoryId(@Param(value = "courseId") Integer courseId, @Param(value = "scheduleId") Integer scheduleId);

    int insertByDeviceId(@Param(value = "course_id") Integer course_id, @Param(value = "device_ids") List<Integer> device_ids);

    CourseDeviceEntity selectByPrimaryKey(Integer id);

    List<CourseResBean> selectByCourseId(Integer id);

    List<CourseResBean> selectByName(@Param(value = "id") Integer id, @Param(value = "name") String name);

    int updateByPrimaryKeySelective(CourseDeviceEntity record);

    //跟新类型 1正向更新 2反向更新
    int updateType(@Param(value = "id") Integer id, @Param(value = "course_id") Integer course_id, @Param(value = "updateType") String updateType);

    List<Integer> selectByCourseDeviceId(@Param(value = "course_id") Integer id, @Param(value = "device_ids") List<Integer> device_ids);

    List<CourseDeviceEntity> selectByCourseDeviceId2(@Param(value = "course_id") Integer id, @Param(value = "device_ids") List<Integer> device_ids);

}
