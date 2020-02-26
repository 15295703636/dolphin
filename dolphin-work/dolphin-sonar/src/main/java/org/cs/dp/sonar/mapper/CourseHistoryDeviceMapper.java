package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.CourseHistoryDeviceEntity;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface CourseHistoryDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseHistoryDeviceEntity record);

    int insertSelective(CourseHistoryDeviceEntity record);

    CourseHistoryDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseHistoryDeviceEntity record);
}
