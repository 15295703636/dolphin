package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.MeetingUserEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MeetingUserMapper {
    int deleteByMeetingId(Integer id);

    int insertBatch(@Param(value = "meetingUsers") List<MeetingUserEntity> meetingUsers);

    int insertSelective(MeetingUserEntity record);

    MeetingUserEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingUserEntity record);

    int updateByPrimaryKey(MeetingUserEntity record);
}
