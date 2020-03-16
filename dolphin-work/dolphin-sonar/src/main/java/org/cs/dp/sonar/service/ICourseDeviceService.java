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

    ReturnInfo setDiscMode(CourseDeviceSpeakReqBean param);

    //设置分屏
    //ReturnInfo setPeerLayout(RestPartyLayoutReqBean param);

    //设置发言
    ReturnInfo speak(CourseDeviceSpeakReqBean param);

    //开启直播
    ReturnInfo startLiveStreaming(String ysx_course_id, CourseSaveResBean courseSave,Integer org_id,Integer customer_id);

    //停止直播
    ReturnInfo stopLiveStreaming(String course_id, Integer type,Integer org_id,Integer customer_id);

    ReturnInfo baioControl(BaioControlReqBean param);
}
