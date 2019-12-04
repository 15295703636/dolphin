package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ChannelEntity;

/**
* @ClassName IChannelService
* @Description 频道分类接口
* @Author LiuJT
* @Date 2019-12-02 05:44:59
**/
public interface IChannelService {

    ReturnInfo addChannel(ChannelEntity param);

    ReturnInfo delChannel(Integer param);

    ReturnInfo editChannel(ChannelEntity param);

    ReturnInfo getChannel(RequestPage<SplitPageInfo, Object> param);

}
