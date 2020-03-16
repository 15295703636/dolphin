package org.cs.dp.ucenter.mapper;

import org.cs.dp.ucenter.domain.entity.CodeEntity;

import java.util.List;

public interface CodeMapper {
    int deleteByPrimaryKey(Integer code_id);

    int insert(CodeEntity record);

    int insertSelective(CodeEntity record);

    List<CodeEntity> select(String tableName);

    int updateByPrimaryKeySelective(CodeEntity record);

}
