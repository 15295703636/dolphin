package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingUserEntity;

/**
* @ClassName IMeetingUserService
* @Description 会议用户管理接口
* @Author LiuJT
* @Date 2019-12-09 01:47:08
**/
public interface IMeetingUserService {

    ReturnInfo addMeetingUser(MeetingUserEntity param);

    ReturnInfo delMeetingUser(Integer param);

    ReturnInfo editMeetingUser(MeetingUserEntity param);

    ReturnInfo getMeetingUser(RequestPage<SplitPageInfo, Object> param);

}
