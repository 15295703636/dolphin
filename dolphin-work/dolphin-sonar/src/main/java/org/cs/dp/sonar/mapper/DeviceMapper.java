package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.GetDeviceBean;
import org.cs.dp.sonar.domain.entity.DeviceEntity;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.sonar.domain.entity.UserEntity;

import java.util.List;

@Mapper
public interface DeviceMapper {
    int deleteByPrimaryKey(@Param(value = "device_id") List<Integer> device_id);

    int insertSelective(DeviceEntity record);

    List<DeviceEntity> selectByPrimaryKey(@Param(value = "ids") List<Integer> ids);

    //
    List<UserEntity> selectUserById(@Param(value = "ids") List<Integer> ids);

    List<DeviceEntity> selectByCondition(GetDeviceBean param);

    int updateByPrimaryKeySelective(DeviceEntity record);

}
