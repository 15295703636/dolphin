package org.cs.dp.sonar.service;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingDeviceEntity;

/**
* @ClassName IMeetingDeviceService
* @Description 会议端管理接口
* @Author LiuJT
* @Date 2019-12-09 01:46:41
**/
public interface IMeetingDeviceService {

    ReturnInfo addMeetingDevice(MeetingDeviceEntity param);

    ReturnInfo delMeetingDevice(Integer param);

    ReturnInfo editMeetingDevice(MeetingDeviceEntity param);

    ReturnInfo getMeetingDevice(RequestPage<SplitPageInfo, Object> param);

}
