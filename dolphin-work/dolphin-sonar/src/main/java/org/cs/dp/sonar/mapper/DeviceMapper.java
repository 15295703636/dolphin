package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {
    int deleteByPrimaryKey(List<Integer> device_id);

    int insertSelective(DeviceEntity record);

    DeviceEntity selectByPrimaryKey(Integer device_id);

    List<DeviceEntity> selectByCondition(GetDeviceBean param);

    int updateByPrimaryKeySelective(DeviceEntity record);

}
