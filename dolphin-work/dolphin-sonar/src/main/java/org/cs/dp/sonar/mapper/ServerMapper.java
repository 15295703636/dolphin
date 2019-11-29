package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.sonar.domain.entity.ServerEntity;

import java.util.List;

/**
 * 服务
 */
@Mapper
public interface ServerMapper {
    int deleteByPrimaryKey(Integer server_id);

    int insert(ServerEntity record);

    int insertSelective(ServerEntity record);

    ServerEntity selectByPrimaryKey(Integer server_id);

    List<ServerEntity> selectByObj(Object param);

    int updateByPrimaryKeySelective(ServerEntity record);

    int updateByPrimaryKey(ServerEntity record);
}