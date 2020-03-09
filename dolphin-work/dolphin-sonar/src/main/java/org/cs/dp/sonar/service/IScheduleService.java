package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.radar.api.entity.RestConfReq;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.domain.ScheduleStartReqBean;

import java.util.Objects;

/**
 * @ClassName IScheduleService
 * @Description 日程管理接口
 * @Author LiuJT
 * @Date 2019-12-09 05:55:10
 **/
public interface IScheduleService {

    ReturnInfo addSchedule(ScheduleArrayBean param) throws BaseException;

    ReturnInfo delSchedule(Integer param);

    ReturnInfo editSchedule(ScheduleArrayBean param) throws BaseException;

    ReturnInfo getSchedule(RequestPage<SplitPageInfo, GetScheduleBean> param);

    ReturnInfo getByDeviceId(Object obj);

    ReturnInfo getById(Integer id);

    ReturnInfo start(ScheduleStartReqBean param);

    void dealPeerLayout(RestConfReq restConfReq, long ysx_course_id,String isLive);

    String[] dealLiving(String uuid,Integer org_id,Integer customer_id);
}
