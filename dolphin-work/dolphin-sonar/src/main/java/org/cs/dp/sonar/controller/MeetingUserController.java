package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingUserEntity;
import org.cs.dp.sonar.service.IMeetingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName MeetingUserController
* @Description 会议用户管理
* @Author LiuJT
* @Date 2019-12-09 01:47:08
**/
@RestController
@RequestMapping("meetingUser")
@Api(tags = "【会议用户管理】")
public class MeetingUserController {
    @Autowired
    private IMeetingUserService iMeetingUserService;

    @PostMapping("add")
    @ApiOperation(value = "添加会议用户管理", notes = "会议用户管理")
    public ReturnInfo addMeetingUser(@RequestBody MeetingUserEntity param){
        return iMeetingUserService.addMeetingUser(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除会议用户管理", notes = "会议用户管理")
    public ReturnInfo delMeetingUser(@RequestBody Integer id){
        return iMeetingUserService.delMeetingUser(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改会议用户管理", notes = "会议用户管理")
    public ReturnInfo editMeetingUser(@RequestBody MeetingUserEntity param){
        return iMeetingUserService.editMeetingUser(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询会议用户管理", notes = "会议用户管理")
    public ReturnInfo getMeetingUser(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iMeetingUserService.getMeetingUser(param);
    }
}
