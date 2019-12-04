package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;

public interface SuperUserMapper {
    int deleteByPrimaryKey(Integer user_id);

    int insertSelective(SuperUserEntity record);

    int updateByPrimaryKeySelective(SuperUserEntity record);

    SuperUserEntity selectByUserName(String name);

    int selectByUserNameCou(String name);
}