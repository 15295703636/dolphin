package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.entity.VideoDemandEntity;
import org.cs.dp.sonar.mapper.VideoDemandMapper;
import org.cs.dp.sonar.service.IVideoDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IVideoDemandServiceImpl
* @Description 点播管理实现类
* @Author LiuJT
* @Date 2020-02-13 10:04:14
**/
@Service
public class IVideoDemandServiceImpl implements IVideoDemandService {
    @Autowired
    private VideoDemandMapper videoDemandMapper;

    @Override
    public ReturnInfo addVideoDemand(VideoDemandEntity param) {
        videoDemandMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delVideoDemand(List<Integer> param) {
        videoDemandMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editVideoDemand(VideoDemandEntity param) {
        videoDemandMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getVideoDemand(RequestPage<SplitPageInfo, String> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<VideoDemandEntity> resList = videoDemandMapper.selectByCon(param.getInfo(),
                ThreadLocalUserInfoUtil.get().getCustomer_id());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}