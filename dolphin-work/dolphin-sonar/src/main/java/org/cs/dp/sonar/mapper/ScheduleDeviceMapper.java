package org.cs.dp.sonar.mapper;

import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleDeviceMapper {
    int deleteByScheduleId(Integer id);

    int insertBatch(@Param(value = "param") List<ScheduleDeviceEntity> param);

    int insertSelective(ScheduleDeviceEntity record);

    ScheduleDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScheduleDeviceEntity record);

    int updateByPrimaryKey(ScheduleDeviceEntity record);
}
