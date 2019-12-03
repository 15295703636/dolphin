package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.DeviceEntity;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Integer device_id);

    int insert(DeviceEntity record);

    int insertSelective(DeviceEntity record);

    DeviceEntity selectByPrimaryKey(Integer device_id);

    List<DeviceEntity> selectByObj(Object param);

    int updateByPrimaryKeySelective(DeviceEntity record);

    int updateByPrimaryKey(DeviceEntity record);
}