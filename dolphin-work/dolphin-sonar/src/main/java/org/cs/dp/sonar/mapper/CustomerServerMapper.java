package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerServerMapper {
    int deleteByPrimaryKey(Integer server_id);

    int insertSelective(CustomerServerEntity record);

    List<CustomerServerEntity> selectByOrgId(
            @Param("org_id") Integer org_id,
            @Param("customer_id") Integer customer_id);

    int updateByPrimaryKeySelective(CustomerServerEntity record);

}
