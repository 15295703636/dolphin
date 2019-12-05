package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerEntity record);

    int insertSelective(CustomerEntity record);

    CustomerEntity selectByPrimaryKey(Integer id);

    int selectByUserNameCou(String name);

    List<CustomerEntity > selectByObj(@Param(value = "name") String name);

    List<CustomerEntity > selectByManageId(@Param(value = "manageId") Integer manageId);

    int updateByPrimaryKeySelective(CustomerEntity record);

    int updateByPrimaryKey(CustomerEntity record);
}
