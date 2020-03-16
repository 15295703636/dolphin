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

    /**
     * 根据组织查询设备
     */
    Integer getDeviceByOrg(@Param(value = "customer_id") Integer customer_id,@Param(value = "org_id")Integer org_id);

    /**
     * 根据组织查询流媒体服务
     */
    Integer getServerByOrg(@Param(value = "customer_id") Integer customer_id,@Param(value = "org_id")Integer org_id);
}
