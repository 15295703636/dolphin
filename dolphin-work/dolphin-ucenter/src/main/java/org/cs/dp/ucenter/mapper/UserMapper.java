package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dp.ucenter.domain.entity.UserEntity;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(List<Integer> userIds);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer user_id);

    UserInfo selectByUserName(String userName);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKeyWithBLOBs(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    List<UserEntity> getListByOrgId(@Param(value = "orgId") int orgId,@Param(value = "userId")int userId);
}