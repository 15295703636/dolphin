package org.cs.dp.ucenter.mapper;

import org.cs.dp.ucenter.domain.entity.SuperUserEntity;

import java.util.List;

public interface SuperUserMapper {

    List<SuperUserEntity> selectManage(Integer manageId);

    int deleteByPrimaryKey(Integer user_id);

    int insertSelective(SuperUserEntity record);

    int updateByPrimaryKeySelective(SuperUserEntity record);

    SuperUserEntity selectByUserName(String name);

    int selectByUserNameCou(String name);
}