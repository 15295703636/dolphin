package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.entity.InteractionEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InteractionMapper {
    int deleteByPrimaryKey(Integer interaction_id);

    int insert(InteractionEntity record);

    int insertSelective(InteractionEntity record);

    List<InteractionEntity> selectByCondition(GetAppSchReqBean param);

    int updateByPrimaryKeySelective(InteractionEntity record);

    int updateByPrimaryKey(InteractionEntity record);
}
