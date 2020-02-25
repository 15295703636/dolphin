package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseHistoryDeviceEntity;
import org.cs.dp.sonar.mapper.CourseHistoryDeviceMapper;
import org.cs.dp.sonar.service.ICourseHistoryDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
* @ClassName ICourseHistoryDeviceServiceImpl
* @Description 日程历史设备实现类
* @Author LiuJT
* @Date 2020-02-25 10:53:08
**/
@Service
public class ICourseHistoryDeviceServiceImpl implements ICourseHistoryDeviceService {
    @Autowired
    private CourseHistoryDeviceMapper courseHistoryDeviceMapper;

    @Override
    public ReturnInfo addCourseHistoryDevice(CourseHistoryDeviceEntity param) {
        courseHistoryDeviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCourseHistoryDevice(Integer param) {
        courseHistoryDeviceMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCourseHistoryDevice(CourseHistoryDeviceEntity param) {
        courseHistoryDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCourseHistoryDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CourseHistoryDeviceEntity> resList = null;//TODO 分页sql要自己实现 courseHistoryDeviceMapper.selectByObj(new HashMap());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }
}