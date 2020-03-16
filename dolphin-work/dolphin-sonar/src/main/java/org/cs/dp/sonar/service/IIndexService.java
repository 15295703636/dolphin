package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.index.IndexCountReqBean;

/**
 * 首页接口
 */
public interface IIndexService {
    ReturnInfo getCourse(Integer type);

    ReturnInfo getSchedule(String getSchedule);

    ReturnInfo getCourseHistory(IndexCountReqBean param);

    ReturnInfo getClaSta();
}
