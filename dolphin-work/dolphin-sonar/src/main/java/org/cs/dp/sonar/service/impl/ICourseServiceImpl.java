package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.UserConditionBean;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.CourseHistoryMapper;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName ICourseServiceImpl
 * @Description 进行中日程管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 11:21:47
 **/
@Service
public class ICourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private CourseHistoryMapper courseHistory;

    @Override
    public ReturnInfo delCourse(Integer param) {
        courseMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCourse(CourseEntity param) {
        courseMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCourse(RequestPage<SplitPageInfo, GetCourseReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());

        UserConditionBean userConditionBean = new UserConditionBean();
        GetCourseReqBean getCourseReqBean = param.getInfo();
        if (null != getCourseReqBean.getOrgId() && 0 != getCourseReqBean.getOrgId()) {
            userConditionBean.setOrgId(getCourseReqBean.getOrgId());
        }
        List<CourseEntity> resList = courseMapper.selectByCondition(getCourseReqBean, userConditionBean);
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    /**
     * 1.讲日程信息添加到日程表
     * 2.将设备添加到关联的设备表
     *
     * @param id
     * @return
     */
    @Override
    public ReturnInfo startSchedule(Integer id) {
        List<ScheduleEntity> resList = scheduleMapper.selectByCondition(new GetScheduleBean(id));
        if (0 == resList.size()) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "选择日程不能在!");
        }
        ScheduleEntity schedule = resList.get(0);

        String currentDateStr = DateUtil.getCurrentDate(DateUtil.YMDHMS);
        CourseEntity course = new CourseEntity(
                null, schedule.getName(), schedule.getType(), schedule.getState(), schedule.getTeacher_name(),
                schedule.getIsRecord(), schedule.getIsLive(), null, null, null, null,
                null, null, null, currentDateStr, null, currentDateStr, null, null,
                null, null, schedule.getResolving_power(), null, null, null, null, 1000,
                null, schedule.getDevice_id(), schedule.getDevice_ids(), null, null,
                null, null, null, null, null, null,
                schedule.getBandwidth());
        courseMapper.insertSelective(course);

        //端控制
        List<String> devices = Arrays.asList(schedule.getDevice_ids().split("，"));
        List<CourseDeviceEntity> courseDevices = new ArrayList<>();
        devices.forEach(o -> courseDevices.add(new CourseDeviceEntity(id, Integer.valueOf(o))));
        courseDeviceMapper.insertBatch(courseDevices);

        return new ReturnInfo();
    }

    @Override
    public ReturnInfo liveBroadcast(Integer id) {
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo share(Integer id) {
        return new ReturnInfo();
    }

    /**
     * 1.将此条数据插入到历史表，并删除该条信息
     *
     * @param id
     * @return
     */
    @Override
    public ReturnInfo end(Integer id) {
        courseHistory.insertSelectCurrent(id);
        return new ReturnInfo();
    }
}