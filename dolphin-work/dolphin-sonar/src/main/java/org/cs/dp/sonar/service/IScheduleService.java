package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.entity.ScheduleEntity;

/**
* @ClassName IScheduleService
* @Description 日程管理接口
* @Author LiuJT
* @Date 2019-12-09 05:55:10
**/
public interface IScheduleService {

    ReturnInfo addSchedule(ScheduleEntity param);

    ReturnInfo delSchedule(Integer param);

    ReturnInfo editSchedule(ScheduleEntity param);

    ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param);

    ReturnInfo liveBroadcast(Integer id);

    ReturnInfo share(Integer id);

    ReturnInfo end(Integer id);
}
