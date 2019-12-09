package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.InteractionDeviceEntity;
import org.cs.dp.sonar.mapper.InteractionDeviceMapper;
import org.cs.dp.sonar.service.IInteractionDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IInteractionDeviceServiceImpl
* @Description 互动-端管理实现类
* @Author LiuJT
* @Date 2019-12-09 03:15:34
**/
@Service
public class IInteractionDeviceServiceImpl implements IInteractionDeviceService {
    @Autowired
    private InteractionDeviceMapper interactionDeviceMapper;

    @Override
    public ReturnInfo addInteractionDevice(InteractionDeviceEntity param) {
        interactionDeviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delInteractionDevice(Integer param) {
        interactionDeviceMapper.deleteByInteractionId(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editInteractionDevice(InteractionDeviceEntity param) {
        interactionDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getInteractionDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<InteractionDeviceEntity> resList = null;//TODO 分页sql要自己实现 interactionDeviceMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}