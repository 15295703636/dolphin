package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseDeviceEntity record);

    int insertBatch(List<CourseDeviceEntity> param);

    int insertSelective(CourseDeviceEntity record);

    CourseDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseDeviceEntity record);

    int updateByPrimaryKey(CourseDeviceEntity record);
}
