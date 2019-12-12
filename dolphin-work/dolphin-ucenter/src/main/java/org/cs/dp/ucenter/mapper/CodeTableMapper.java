package org.cs.dp.ucenter.mapper;

import org.cs.dp.ucenter.domain.entity.CodeTableEntity;

import java.util.List;

public interface CodeTableMapper {
    int deleteByPrimaryKey(String table_name);

    int insert(CodeTableEntity record);

    int insertSelective(CodeTableEntity record);

    List<CodeTableEntity> selectByExplain(String explain);

    int updateByPrimaryKeySelective(CodeTableEntity record);

}
