package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.CustomerServerEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerServerMapper {
    int deleteByPrimaryKey(Integer server_id);

    int insertSelective(CustomerServerEntity record);

    List<CustomerServerEntity> selectByCondition(Integer server_id);

    int updateByPrimaryKeySelective(CustomerServerEntity record);

}
