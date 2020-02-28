package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.AddGetIdBean;
import org.cs.dp.sonar.domain.GetCourseHistoryReqBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.ScheduleOneDeviceBean;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CourseHistoryEntity record);

    int insertSelectCurrent(AddGetIdBean apram);

    CourseHistoryEntity selectByPrimaryKey(Integer id);

    List<CourseHistoryEntity> selectByCondition(GetCourseHistoryReqBean param);

    //返回云视讯设备id
    ScheduleOneDeviceBean selectByIdResYsx(Integer id);

    ScheduleOneDeviceBean selectById(Integer id);

    int updateByPrimaryKeySelective(CourseHistoryEntity record);

}
