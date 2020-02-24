package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;

/**
* @ClassName IScheduleService
* @Description 日程管理接口
* @Author LiuJT
* @Date 2019-12-09 05:55:10
**/
public interface IScheduleService {

    ReturnInfo addSchedule(ScheduleArrayBean param);

    ReturnInfo delSchedule(Integer param);

    ReturnInfo editSchedule(ScheduleArrayBean param);

    ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param);

    ReturnInfo getById(Integer id);

    ReturnInfo start(Integer id);
}
