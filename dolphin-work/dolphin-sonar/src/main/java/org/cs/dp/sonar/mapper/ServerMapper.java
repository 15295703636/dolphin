package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.ServerEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServerMapper {
    int deleteByPrimaryKey(Integer server_id);

    int insertSelective(ServerEntity record);

    ServerEntity selectByPrimaryKey(Integer server_id);

    List<ServerEntity> selectAll();

    int updateByPrimaryKeySelective(ServerEntity record);

}
