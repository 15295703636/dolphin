package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.InteractionDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InteractionDeviceMapper {
    int deleteByInteractionId(Integer id);

    int insert(InteractionDeviceEntity record);

    int insertBatch(@Param(value = "param") List<InteractionDeviceEntity> param);

    int insertSelective(InteractionDeviceEntity record);

    InteractionDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InteractionDeviceEntity record);

    int updateByPrimaryKey(InteractionDeviceEntity record);
}
