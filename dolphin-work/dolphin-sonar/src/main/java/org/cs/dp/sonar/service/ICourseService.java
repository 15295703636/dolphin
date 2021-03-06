package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.domain.entity.CourseEntity;

/**
 * @ClassName ICourseService
 * @Description 进行中日程管理接口
 * @Author LiuJT
 * @Date 2019-12-10 11:21:47
 **/
public interface ICourseService {

    CourseSaveResBean startCourser(ScheduleStartReqBean param, Long ysx_id);

    ReturnInfo delCourse(Integer param);

    ReturnInfo editCourse(CourseEntity param);

    ReturnInfo getCourse(RequestPage<SplitPageInfo, GetCourseReqBean> param);

    ReturnInfo getById(Integer id);

    ReturnInfo getDeviceByNameState(GetDeviceByNameStateReqBean param);

    ReturnInfo liveBroadcast(Integer id);

    ReturnInfo share(Integer id);

    ReturnInfo end(CourseEndReqBean param) throws BaseException;

    String exit(Integer device_id);
}
