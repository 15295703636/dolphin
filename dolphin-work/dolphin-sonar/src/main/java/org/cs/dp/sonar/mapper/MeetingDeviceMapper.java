package org.cs.dp.sonar.mapper;

import org.apache.ibatis.annotations.Param;
import org.cs.dp.sonar.domain.entity.MeetingDeviceEntity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingDeviceMapper {
    int deleteByMeetingId(Integer id);

    int insert(MeetingDeviceEntity record);

    int insertBatch(@Param(value = "meetingDevices") List<MeetingDeviceEntity> meetingDevices);

    int insertSelective(MeetingDeviceEntity record);

    MeetingDeviceEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingDeviceEntity record);

    int updateByPrimaryKey(MeetingDeviceEntity record);
}
