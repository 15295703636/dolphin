package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.DateUtil;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.index.IndexCountReqBean;
import org.cs.dp.sonar.domain.index.IndexCountDateResBean;
import org.cs.dp.sonar.domain.index.IndexCountResBean;
import org.cs.dp.sonar.mapper.*;
import org.cs.dp.sonar.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName IIndexServiceImpl
 * @Description 首页实现
 * @Author Liujt
 * @Date 2020/3/6 9:51
 **/
@Service
public class IIndexServiceImpl implements IIndexService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseDeviceMapper courseDeviceMapper;
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    @Autowired
    private CourseHistoryMapper courseHistoryMapper;

    @Override
    public ReturnInfo<List<CourseGetByIdResBean>> getCourse(Integer type) {
        List<CourseGetByIdResBean> courses = courseMapper.selectByCustomerId(ThreadLocalUserInfoUtil.get().getCustomer_id(), type);
        for (CourseGetByIdResBean course : courses) {
            //查询会议信息
            if (!"3".equals(course.getCourse_type())) {
                List<CourseResBean> courseDevices = courseDeviceMapper.selectByCourseId(course.getCourse_id());
                List<String> courseDeviceList = new ArrayList<>();
                if (courseDevices.size() > 0) {
                    courseDevices.forEach(e -> {
                        if (e.getIsMain().equals(1)) {
                            course.setMainName(e.getName());
                        } else if (!e.getIsMain().equals(1)) {
                            courseDeviceList.add(e.getName());
                        }
                    });
                    course.setRemoteDeviceName(courseDeviceList);
                }
            } else {
                course.setMain(
                        JSONObject.parseObject(
                                JSON.toJSONString(deviceMapper.selectByPrimaryKey(Arrays.asList(course.getLocal_classroomId())).get(0))
                                , CourseResBean.class)
                );
            }
        }
        return new ReturnInfo(courses);
    }

    @Override
    public ReturnInfo getSchedule(String date) {
        GetScheduleBean getScheduleBean = new GetScheduleBean();
        getScheduleBean.setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        getScheduleBean.setDate(date);
        List<ScheduleArrayBean> res = (List<ScheduleArrayBean>) scheduleMapper.selectByCustomerId(getScheduleBean);
        res.forEach(e -> {
            String duration[] = e.getDuration().split(",");
            e.setDuration_hour(Integer.valueOf(duration[0]));
            e.setDuration_minute(Integer.valueOf(duration[1]));
        });
        return new ReturnInfo(res);
    }

    @Override
    public ReturnInfo getCourseHistory(IndexCountReqBean param) {
        if (null != param.getType()) {
            int startInt = -1;
            int endInt = 0;
            switch (param.getType()) {
                case 1:
                    endInt = 8;
                    break;
                case 2:
                    endInt = 16;
                    break;
                case 3:
                    endInt = 31;
                    break;
            }
            param.setStartTime(DateUtil.dateToString(DateUtil.addDate(new Date().getTime(), startInt, DateUtil.DAY), DateUtil.YMDHMS));
            param.setEndTime(DateUtil.dateToString(DateUtil.addDate(new Date().getTime(), endInt, DateUtil.DAY), DateUtil.YMDHMS));
        }
        List<IndexCountDateResBean> indexCounts = courseHistoryMapper.getDate(param);

        Map<String, List<IndexCountDateResBean>> indexCountMap = indexCounts.stream().collect(Collectors.groupingBy(IndexCountDateResBean::getDate));

        IndexCountResBean indexCountRes = new IndexCountResBean();
        indexCountRes.setIcons(indexCountMap);
        //Map map = courseHistoryMapper.getCount(param);
        List<IndexCountDateResBean> types = courseHistoryMapper.getType(param);
        indexCountRes.setTypeIcon(types);
        long total = 0;
        long totalSun = 0;
        for (IndexCountDateResBean indexCount : types) {
            total += indexCount.getDuration();
            totalSun += indexCount.getSum();
        }

        Map map = new HashMap();
        map.put("duration", total);
        map.put("num", totalSun);

        indexCountRes.setTotal(map);
        return new ReturnInfo(indexCountRes);
    }

    public ReturnInfo getClaSta() {
        return new ReturnInfo<>(courseHistoryMapper.getClaSta(ThreadLocalUserInfoUtil.get().getCustomer_id()));
    }
}
