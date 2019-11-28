package org.cs.dp.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dp.ucenter.domain.GetLogBean;

import java.util.List;

@Mapper
public interface LogMapper {
    int deleteByDay(Integer day);

    int insertSelective(LogEntity record);

    List<LogEntity> selectByTerm(GetLogBean param);

}