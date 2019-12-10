package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseEntity;

/**
* @ClassName ICourseService
* @Description 进行中日程管理接口
* @Author LiuJT
* @Date 2019-12-10 11:21:47
**/
public interface ICourseService {

    ReturnInfo startSchedule(Integer id);

    ReturnInfo delCourse(Integer param);

    ReturnInfo editCourse(CourseEntity param);

    ReturnInfo getCourse(RequestPage<SplitPageInfo, Object> param);

}
