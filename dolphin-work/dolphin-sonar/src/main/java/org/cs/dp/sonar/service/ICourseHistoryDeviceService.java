package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseHistoryDeviceEntity;

/**
* @ClassName ICourseHistoryDeviceService
* @Description 日程历史设备接口
* @Author LiuJT
* @Date 2020-02-25 10:53:08
**/
public interface ICourseHistoryDeviceService {

    ReturnInfo addCourseHistoryDevice(CourseHistoryDeviceEntity param);

    ReturnInfo delCourseHistoryDevice(Integer param);

    ReturnInfo editCourseHistoryDevice(CourseHistoryDeviceEntity param);

    ReturnInfo getCourseHistoryDevice(RequestPage<SplitPageInfo, Object> param);

}
