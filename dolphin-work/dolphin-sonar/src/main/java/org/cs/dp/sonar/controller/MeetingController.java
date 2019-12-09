package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.AddMeetingBean;
import org.cs.dp.sonar.domain.entity.MeetingEntity;
import org.cs.dp.sonar.service.IMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName MeetingController
* @Description 会议管理
* @Author LiuJT
* @Date 2019-12-09 01:34:31
**/
@RestController
@RequestMapping("meeting")
@Api(tags = "【会议管理】")
public class MeetingController {
    @Autowired
    private IMeetingService iMeetingService;

    @PostMapping("add")
    @ApiOperation(value = "添加会议管理", notes = "会议管理")
    public ReturnInfo addMeeting(@RequestBody AddMeetingBean param){
        return iMeetingService.addMeeting(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除会议管理", notes = "会议管理")
    public ReturnInfo delMeeting(@RequestBody Integer id){
        return iMeetingService.delMeeting(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改会议管理", notes = "会议管理")
    public ReturnInfo editMeeting(@RequestBody MeetingEntity param){
        return iMeetingService.editMeeting(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询会议管理", notes = "会议管理")
    public ReturnInfo getMeeting(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iMeetingService.getMeeting(param);
    }
}
