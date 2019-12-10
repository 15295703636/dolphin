package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.service.ICourseDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ICourseDeviceServiceImpl
 * @Description 日程-端管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 01:11:55
 **/
@Service
public class ICourseDeviceServiceImpl implements ICourseDeviceService {
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;

    @Override
    public ReturnInfo addCourseDevice(CourseDeviceEntity param) {
        courseDeviceMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCourseDevice(Integer param) {
        courseDeviceMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCourseDevice(CourseDeviceEntity param) {
        courseDeviceMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCourseDevice(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CourseDeviceEntity> resList = courseDeviceMapper.selectByCondition();
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo mute(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo cancelMute(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo connect(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo hangUp(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo sidelines(Integer id) {
        return new ReturnInfo();
    }
}