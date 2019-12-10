package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;

/**
* @ClassName ICourseDeviceService
* @Description 日程-端管理接口
* @Author LiuJT
* @Date 2019-12-10 01:11:55
**/
public interface ICourseDeviceService {

    ReturnInfo addCourseDevice(CourseDeviceEntity param);

    ReturnInfo delCourseDevice(Integer param);

    ReturnInfo editCourseDevice(CourseDeviceEntity param);

    ReturnInfo getCourseDevice(RequestPage<SplitPageInfo, Object> param);

    ReturnInfo mute(Integer id);

    ReturnInfo cancelMute(Integer id);
}
