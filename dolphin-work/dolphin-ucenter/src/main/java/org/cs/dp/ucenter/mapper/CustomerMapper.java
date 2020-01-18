package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.EditStatusBean;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.domain.entity.UserEntity;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(List<Integer> id);

    int insert(CustomerEntity record);

    int insertSelective(CustomerEntity record);

    CustomerEntity selectByPrimaryKey(Integer id);

    int selectByUserNameCou(String name);

    UserEntity getCusAdminInfo(Integer id);

    List<CustomerEntity > selectByObj(@Param(value = "name") String name,
                                      @Param(value = "state") String state);

    List<CustomerEntity > selectByManageId(@Param(value = "manageId") Integer manageId);

    int updateByPrimaryKeySelective(CustomerEntity record);

    int updateStatusByKey(@Param(value = "param") EditStatusBean param);
}
