package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.GetCourseHistoryReqBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.CourseHistoryMapper;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.service.ICourseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ICourseHistoryServiceImpl
 * @Description 日程历史管理实现类
 * @Author LiuJT
 * @Date 2019-12-10 02:56:38
 **/
@Service
public class ICourseHistoryServiceImpl implements ICourseHistoryService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private CourseHistoryMapper courseHistoryMapper;

    @Override
    public ReturnInfo addCourseHistory(CourseHistoryEntity param) {
        courseHistoryMapper.insertSelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delCourseHistory(Integer param) {
        courseHistoryMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editCourseHistory(CourseHistoryEntity param) {
        courseHistoryMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getCourseHistory(RequestPage<SplitPageInfo, GetCourseHistoryReqBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<CourseHistoryEntity> resList = courseHistoryMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo start(Integer id, Long ysx_id) {
        ScheduleArrayBean res = courseHistoryMapper.selectById(id);
        if (null == res) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "选择日程不能在!");
        }

        CourseEntity course = new CourseEntity();
        course.setCourse_name(res.getName());
        course.setCourse_type(res.getType());
        course.setTeacher_name(res.getTeacher_name());
        course.setIsLive(res.getIsLive());
        course.setIsRecord(res.getIsRecord());
        course.setBandwidth(res.getBandwidth());
        course.setCreaterId(ThreadLocalUserInfoUtil.get().getUser_id());
        course.setOrg_id(res.getOrg_id());
        course.setYsx_id(ysx_id);
        String currentDateStr = DateUtil.getCurrentDate(DateUtil.YMDHMS);
        course.setStart_time(currentDateStr);
        course.setUser_number(res.getUser_number());
        course.setLocal_classroomId(res.getDevice_id());
        course.setDuration(res.getDuration());
        courseMapper.insertSelective(course);

        //端控制
        courseDeviceMapper.insertByHistoryId(course.getCourse_id(), id);
        return new ReturnInfo();
    }

}