package org.cs.dp.ucenter.mapper;

import org.cs.dp.ucenter.domain.entity.UserOrgEntity;

public interface UserOrgMapper {
    int deleteByPrimaryKey(Integer user2org_id);

    int insert(UserOrgEntity record);

    int insertSelective(UserOrgEntity record);

    UserOrgEntity selectByPrimaryKey(Integer user2org_id);

    int updateByPrimaryKeySelective(UserOrgEntity record);

    int updateByPrimaryKey(UserOrgEntity record);
}
