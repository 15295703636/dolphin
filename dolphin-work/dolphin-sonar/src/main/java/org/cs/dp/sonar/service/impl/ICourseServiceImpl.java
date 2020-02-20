package org.cs.dp.sonar.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.mapper.CourseDeviceMapper;
import org.cs.dp.sonar.mapper.CourseHistoryMapper;
import org.cs.dp.sonar.mapper.CourseMapper;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        getCourseReqBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        if (null != getCourseReqBean.getOrg_id() && 0 != getCourseReqBean.getOrg_id()) {
            userConditionBean.setOrgId(getCourseReqBean.getOrg_id());
        }
        List<CourseEntity> resList = courseMapper.selectByCondition(getCourseReqBean);
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        return new ReturnInfo(splitPageInfo, resList);
    }

    public ReturnInfo getById(Integer id) {

        CourseGetByIdResBean courseGetByIdRes = courseMapper.selectById(id);

        List<CourseResBean> courseDevices = courseDeviceMapper.selectByCourseId(id);
        List<CourseResBean> courseDeviceList = new ArrayList<>();
        if (courseDevices.size() > 0) {
            CourseResBean courseRes = courseDevices.stream().filter(
                    e -> e.getIsMain().equals(0)).collect(Collectors.toList()).get(0);
            courseDevices.remove(courseRes);
            courseDevices.forEach(e -> {
                if (!e.getDevice_id().equals(courseRes.getDevice_id())) {
                    courseDeviceList.add(e);
                }
            });
            courseGetByIdRes.setRemote(courseDeviceList);
            courseGetByIdRes.setMain(courseRes);
        }

        return new ReturnInfo(courseGetByIdRes);
    }

    /**
     * 1.讲日程信息添加到日程表
     * 2.将设备添加到关联的设备表
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, BaseException.class})
    public ReturnInfo startSchedule(Integer id) {
        ScheduleArrayBean res = scheduleMapper.selectById(id);
        if (null == res) {
            return new ReturnInfo(MessageCode.COMMON_DATA_UNNORMAL, "选择日程不能在!");
        }

        String currentDateStr = DateUtil.getCurrentDate(DateUtil.YMDHMS);
        CourseEntity course = new CourseEntity(
                null, res.getName(), res.getType(), res.getState(), res.getTeacher_name(),
                res.getIsRecord(), res.getIsLive(), null, null, null, null,
                null, null, null, currentDateStr, null, currentDateStr, null, null,
                null, null, res.getResolving_power(), null, null, null, null, 1000,
                null, res.getDevice_id(), res.getDevice_ids(), null, null,
                null, null, null, null, null, null,
                res.getBandwidth(), res.getOrg_id());
        courseMapper.insertSelective(course);

        //端控制
        courseDeviceMapper.insertByScheduleId(course.getCourse_id(), id);

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