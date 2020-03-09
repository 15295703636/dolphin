package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.entity.UserApplyEntity;

import java.util.List;

public interface UserApplyMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insertSelective(UserApplyEntity record);

    UserApplyEntity selectByPrimaryKey(Integer user_id);

    List<UserApplyEntity> selectAll(@Param(value = "apply_result") Integer apply_result, @Param(value = "customer_id") Integer customer_id);

    int updateByPrimaryKeySelective(UserApplyEntity record);

}
