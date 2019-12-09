package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.domain.entity.MeetingEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingMapper {
    int deleteByPrimaryKey(Integer meeting_id);

    int insert(MeetingEntity record);

    int insertSelective(MeetingEntity record);

    List<MeetingEntity> selectByCondition(GetAppSchReqBean param);

    int updateByPrimaryKeySelective(MeetingEntity record);

    int updateByPrimaryKey(MeetingEntity record);
}
