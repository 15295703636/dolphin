package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.ucenter.domain.CheckAddInfoReqBean;
import org.cs.dp.ucenter.domain.entity.SuperUserEntity;

import java.util.List;

public interface SuperUserMapper {

    List<SuperUserEntity> selectManage(@Param(value = "manageId") Integer manageId, @Param(value = "user_name") String user_name);

    CheckAddInfoReqBean checkAddInfo(CheckAddInfoReqBean record);

    int deleteByPrimaryKey(@Param(value = "user_ids") List<Integer> user_ids);

    int insertSelective(SuperUserEntity record);

    int updateByPrimaryKeySelective(SuperUserEntity record);

    SuperUserEntity selectByUserName(String name);

    int selectByUserNameCou(String name);
}