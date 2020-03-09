package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dolphin.common.base.UserInfo;
import org.cs.dp.ucenter.domain.GetUserListResBean;
import org.cs.dp.ucenter.domain.GetUserReqBean;
import org.cs.dp.ucenter.domain.UserCountBean;
import org.cs.dp.ucenter.domain.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(@Param(value = "userIds") List<Integer> userIds);

    int insert(UserEntity record);

    int insertSelectApply(@Param(value = "user_id") Integer user_id, @Param(value = "user_name") String user_name);

    int insertSelective(UserEntity record);

    List<UserEntity> selectByPrimaryKey(@Param(value = "userIds") List<Integer> userIds);

    List<UserCountBean> getUserCount(Integer customer_id);

    UserInfo selectByUserName(String userName);

    List<UserEntity> selectByUserNameEmailTel(UserEntity param);

    int updateByPrimaryKeySelective(UserEntity record);

    List<UserEntity> getListByOrgId(@Param(value = "orgId") int orgId, @Param(value = "userId") int userId);

    List<GetUserListResBean> getUsersList(GetUserReqBean userReqBean);

    UserEntity checkUserInfo(@Param(value = "user_id") Integer user_id, @Param(value = "user_name") String user_name);
}