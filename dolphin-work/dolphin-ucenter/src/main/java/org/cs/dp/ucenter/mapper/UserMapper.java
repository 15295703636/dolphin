package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dp.ucenter.api.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    public List<User> getList();
}
