package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.entity.ServerEntity;

/**
 * Sonar模块查询
 */
public interface SonarMapper {
    /**
     * 根据类型查询服务信息
     * @param server_type
     * @return
     */
    ServerEntity getServerByType(@Param(value = "server_type") Integer server_type);
}
