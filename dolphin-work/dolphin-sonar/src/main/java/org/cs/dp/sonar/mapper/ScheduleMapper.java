package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper<T> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ScheduleArrayBean record);

    List<T> selectByCondition(@Param(value = "param") GetScheduleBean param);

    ScheduleArrayBean selectById(@Param(value = "id") Integer id);

    int updateByPrimaryKeySelective(ScheduleEntity record);
}
