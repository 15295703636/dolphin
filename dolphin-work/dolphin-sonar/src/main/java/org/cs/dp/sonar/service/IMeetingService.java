package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.AddMeetingBean;
import org.cs.dp.sonar.domain.entity.MeetingEntity;

/**
* @ClassName IMeetingService
* @Description 会议管理接口
* @Author LiuJT
* @Date 2019-12-09 01:34:31
**/
public interface IMeetingService {

    ReturnInfo addMeeting(AddMeetingBean param);

    ReturnInfo delMeeting(Integer param);

    ReturnInfo editMeeting(MeetingEntity param);

    ReturnInfo getMeeting(RequestPage<SplitPageInfo, Object> param);

}
