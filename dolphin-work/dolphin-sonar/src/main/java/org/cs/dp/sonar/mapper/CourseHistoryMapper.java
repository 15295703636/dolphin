package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CourseHistoryEntity record);

    int insertSelectCurrent(Integer id);

    CourseHistoryEntity selectByPrimaryKey(Integer id);

    List<CourseHistoryEntity> selectByCondition();

    int updateByPrimaryKeySelective(CourseHistoryEntity record);

}
