package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseDeviceEntity;

/**
* @ClassName ICourseDeviceService
* @Description 日程-端管理接口
* @Author LiuJT
* @Date 2019-12-10 01:11:55
**/
public interface ICourseDeviceService {

    ReturnInfo addCourseDevice(CourseDeviceAddReqBean param);

    ReturnInfo delCourseDevice(CourseDeviceReqBean param);

    ReturnInfo editCourseDevice(CourseDeviceEntity param);

    ReturnInfo getCourseDevice(Integer id);

    ReturnInfo mute(CourseDeviceMuteReqBean param);

    ReturnInfo cancelMute(Integer id);

    ReturnInfo connect(CourseDeviceReqBean param);

    ReturnInfo sidelines(Integer id);

    ReturnInfo setLecturer(CourseDeviceSetLecturerReqBean id);

    ReturnInfo setDiscMode(String ysx_course_id);

    //设置分屏
    ReturnInfo setPeerLayout(RestPartyLayoutReqBean param);
}
