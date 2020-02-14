package org.cs.dp.sonar.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.utils.ThreadLocalUserInfoUtil;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.mapper.ScheduleMapper;
import org.cs.dp.sonar.service.ICourseService;
import org.cs.dp.sonar.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private ICourseService iCourseService;

    @Override
    public ReturnInfo addSchedule(ScheduleArrayBean param) {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        param.setDuration(param.getDuration_hour() + "," + param.getDuration_minute());
        param.setCreate_user_id(ThreadLocalUserInfoUtil.get().getUser_id());
        scheduleMapper.insertSelective(param);
        if (param.isNew_start()) {
            iCourseService.startSchedule(param.getId());
        }
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo delSchedule(Integer param) {
        scheduleMapper.deleteByPrimaryKey(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo editSchedule(ScheduleArrayBean param) {
        param.setDevice_ids(JSONArray.toJSONString(param.getDeviceIds()));
        param.setUser_ids(JSONArray.toJSONString(param.getUserIds()));
        scheduleMapper.updateByPrimaryKeySelective(param);
        return new ReturnInfo();
    }

    @Override
    public ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param) {
        SplitPageInfo splitPageInfo = param.getPage();
        PageHelper.startPage(splitPageInfo.getCurrPage(), splitPageInfo.getPerPageNum());
        param.getInfo().setCustomer_id(ThreadLocalUserInfoUtil.get().getCustomer_id());
        List<ScheduleArrayBean> resList = (List<ScheduleArrayBean>) scheduleMapper.selectByCondition(param.getInfo());
        PageInfo p = new PageInfo(resList);
        splitPageInfo.setTotals((int) p.getTotal());
        resList.forEach(e -> {
            String duration[] = e.getDuration().split(",");
            e.setDeviceIds(JSONArray.parseArray(e.getDevice_ids(), Integer.class));
            e.setUserIds(JSONArray.parseArray(e.getUser_ids(), Integer.class));
            e.setDuration_hour(Integer.valueOf(duration[0]));
            e.setDuration_minute(Integer.valueOf(duration[1]));
        });
        return new ReturnInfo(splitPageInfo, resList);
    }

    @Override
    public ReturnInfo getById(Integer id) {
        GetScheduleBean getSchedule = new GetScheduleBean();
        getSchedule.setId(id);
        List<ScheduleArrayBean> resList = (List<ScheduleArrayBean>) scheduleMapper.selectByCondition(getSchedule);
        return new ReturnInfo(resList.get(0));
    }
}