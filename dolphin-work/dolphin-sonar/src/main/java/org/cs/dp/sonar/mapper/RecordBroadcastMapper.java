package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.RecordBroadcastEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordBroadcastMapper {
    int deleteByPrimaryKey(Integer rb_id);

    int insert(RecordBroadcastEntity record);

    int insertSelective(RecordBroadcastEntity record);

    List<RecordBroadcastEntity> selectAll();

    int updateByPrimaryKeySelective(RecordBroadcastEntity record);

    int updateByPrimaryKey(RecordBroadcastEntity record);
}
