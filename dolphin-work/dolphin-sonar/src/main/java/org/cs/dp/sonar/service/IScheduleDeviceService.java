package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;

/**
* @ClassName IScheduleDeviceService
* @Description 日程-端管理接口
* @Author LiuJT
* @Date 2020-02-18 11:23:56
**/
public interface IScheduleDeviceService {

    ReturnInfo addScheduleDevice(ScheduleDeviceEntity param);

    ReturnInfo delScheduleDevice(Integer param);

    ReturnInfo editScheduleDevice(ScheduleDeviceEntity param);

    ReturnInfo getScheduleDevice(RequestPage<SplitPageInfo, Object> param);

}
