package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;

/**
* @ClassName ICourseHistoryService
* @Description 日程历史管理接口
* @Author LiuJT
* @Date 2019-12-10 02:56:38
**/
public interface ICourseHistoryService {

    ReturnInfo addCourseHistory(CourseHistoryEntity param);

    ReturnInfo delCourseHistory(Integer param);

    ReturnInfo editCourseHistory(CourseHistoryEntity param);

    ReturnInfo getCourseHistory(RequestPage<SplitPageInfo, Object> param);

}
