package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName IScheduleServiceImpl
 * @Description 日程管理实现类
 * @Author LiuJT
 * @Date 2019-12-09 05:55:10
 **/
@Service
public class IScheduleServiceImpl implements IScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public ReturnInfo addSchedule(ScheduleEntity param) {
        scheduleMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delSchedule(Integer param) {
        scheduleMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editSchedule(ScheduleEntity param) {
        scheduleMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<ScheduleEntity> resList = scheduleMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}