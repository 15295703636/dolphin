package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.domain.UserEntity;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Integer user_id);

    UserEntity selectByUserName(String userName);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKeyWithBLOBs(UserEntity record);

    int updateByPrimaryKey(UserEntity record);

    List<UserEntity> getList();
}