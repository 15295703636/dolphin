package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.RecordBroadcastEntity;
import org.cs.dp.sonar.mapper.RecordBroadcastMapper;
import org.cs.dp.sonar.service.IRecordBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IRecordBroadcastServiceImpl
* @Description 录播管理实现类
* @Author LiuJT
* @Date 2019-12-09 03:14:27
**/
@Service
public class IRecordBroadcastServiceImpl implements IRecordBroadcastService {
    @Autowired
    private RecordBroadcastMapper recordBroadcastMapper;

    @Override
    public ReturnInfo addRecordBroadcast(RecordBroadcastEntity param) {
        recordBroadcastMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delRecordBroadcast(Integer param) {
        recordBroadcastMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editRecordBroadcast(RecordBroadcastEntity param) {
        recordBroadcastMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getRecordBroadcast(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<RecordBroadcastEntity> resList = recordBroadcastMapper.selectAll();
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}