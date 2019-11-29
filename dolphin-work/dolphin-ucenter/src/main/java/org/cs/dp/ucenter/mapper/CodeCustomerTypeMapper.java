package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.domain.entity.CodeCustomerTypeEntity;

import java.util.List;

@Mapper
public interface CodeCustomerTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CodeCustomerTypeEntity record);

    int insertSelective(CodeCustomerTypeEntity record);

    List<CodeCustomerTypeEntity> selectAll();

    int updateByPrimaryKeySelective(CodeCustomerTypeEntity record);

    int updateByPrimaryKey(CodeCustomerTypeEntity record);
}