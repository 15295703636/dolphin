package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.entity.OrganizationEntity;

import java.util.List;

public interface OrganizationMapper {
    int deleteByPrimaryKey(@Param("ids") List<Integer> ids, @Param("customerId") Integer customerId);

    int insertSelective(OrganizationEntity record);

    int selectByCusIdCou(Integer customer_id);

    OrganizationEntity selectByPrimaryKey(Integer org_id);

    int updateByPrimaryKeySelective(OrganizationEntity record);

    List<OrganizationEntity> getList(OrganizationEntity param);

    List<Integer> getChildIdByParentId(Integer org_id);
}