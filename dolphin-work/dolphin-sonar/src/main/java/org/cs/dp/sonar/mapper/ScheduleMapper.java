package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ScheduleEntity record);

    List<ScheduleEntity> selectByCondition(GetScheduleBean param);

    int updateByPrimaryKeySelective(ScheduleEntity record);
}
