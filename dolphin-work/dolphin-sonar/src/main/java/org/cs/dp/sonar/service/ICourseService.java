package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.sonar.domain.CourseEndReqBean;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;

import java.util.Map;

/**
 * @ClassName ICourseService
 * @Description 进行中日程管理接口
 * @Author LiuJT
 * @Date 2019-12-10 11:21:47
 **/
public interface ICourseService {

    ReturnInfo startSchedule(Integer id,Long ysx_id);

    ReturnInfo delCourse(Integer param);

    ReturnInfo editCourse(CourseEntity param);

    ReturnInfo getCourse(RequestPage<SplitPageInfo, GetCourseReqBean> param);

    ReturnInfo getById(Integer id);

    ReturnInfo liveBroadcast(Integer id);

    ReturnInfo share(Integer id);

    ReturnInfo end(CourseEndReqBean param) throws BaseException;
}
