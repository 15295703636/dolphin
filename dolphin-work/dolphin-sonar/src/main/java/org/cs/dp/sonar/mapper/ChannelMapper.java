package org.cs.dp.sonar.mapper;

import org.cs.dp.sonar.domain.entity.ChannelEntity;
public interface ChannelMapper {
    int deleteByPrimaryKey(Integer channel_id);

    int insert(ChannelEntity record);

    int insertSelective(ChannelEntity record);

    ChannelEntity selectByPrimaryKey(Integer channel_id);

    int updateByPrimaryKeySelective(ChannelEntity record);

    int updateByPrimaryKey(ChannelEntity record);
}
