package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ChannelEntity;
import org.cs.dp.sonar.mapper.ChannelMapper;
import org.cs.dp.sonar.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IChannelServiceImpl
* @Description 频道分类实现类
* @Author LiuJT
* @Date 2019-12-02 05:44:59
**/
@Service
public class IChannelServiceImpl implements IChannelService {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public ReturnInfo addChannel(ChannelEntity param) {
        channelMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delChannel(Integer param) {
        channelMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editChannel(ChannelEntity param) {
        channelMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getChannel(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<ChannelEntity> resList = null;//TODO 分页sql要自己实现 channelMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}