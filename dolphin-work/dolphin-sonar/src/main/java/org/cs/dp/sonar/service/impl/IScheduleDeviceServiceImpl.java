package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;
import org.cs.dp.sonar.mapper.ScheduleDeviceMapper;
import org.cs.dp.sonar.service.IScheduleDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName IScheduleDeviceServiceImpl
* @Description 日程-端管理实现类
* @Author LiuJT
* @Date 2020-02-18 11:23:56
**/
@Service
public class IScheduleDeviceServiceImpl implements IScheduleDeviceService {
    @Autowired
    private ScheduleDeviceMapper scheduleDeviceMapper;

    @Override
    public ReturnInfo addScheduleDevice(ScheduleDeviceEntity param) {
        scheduleDeviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delScheduleDevice(Integer param) {
        scheduleDeviceMapper.deleteByScheduleId(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editScheduleDevice(ScheduleDeviceEntity param) {
        scheduleDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getScheduleDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<ScheduleDeviceEntity> resList = null;//TODO 分页sql要自己实现 scheduleDeviceMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}