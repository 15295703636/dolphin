package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * ucenter模块数据库操作
 */
public interface UcenterMapper {

    /**
     * 根据用户查询用户密码
     * @param userId
     * @return
     */
    String selectById(@Param(value = "userId") Integer userId);

}
