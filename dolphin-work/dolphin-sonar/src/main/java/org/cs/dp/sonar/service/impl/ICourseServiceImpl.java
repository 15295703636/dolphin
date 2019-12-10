package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ReturnInfo getCourse(RequestPage<SplitPageInfo, Object> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        List<CourseEntity> resList = null;//TODO 分页sql要自己实现 courseMapper.selectByObj(new HashMap());
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

        courseMapper.insert(course);

        return new ReturnInfo();
    }
}