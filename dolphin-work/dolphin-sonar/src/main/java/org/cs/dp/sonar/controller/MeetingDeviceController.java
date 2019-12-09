package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.MeetingDeviceEntity;
import org.cs.dp.sonar.service.IMeetingDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName MeetingDeviceController
* @Description 会议端管理
* @Author LiuJT
* @Date 2019-12-09 01:46:41
**/
@RestController
@RequestMapping("meetingDevice")
@Api(tags = "【会议端管理】")
public class MeetingDeviceController {
    @Autowired
    private IMeetingDeviceService iMeetingDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加会议端管理", notes = "会议端管理")
    public ReturnInfo addMeetingDevice(@RequestBody MeetingDeviceEntity param){
        return iMeetingDeviceService.addMeetingDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除会议端管理", notes = "会议端管理")
    public ReturnInfo delMeetingDevice(@RequestBody Integer id){
        return iMeetingDeviceService.delMeetingDevice(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改会议端管理", notes = "会议端管理")
    public ReturnInfo editMeetingDevice(@RequestBody MeetingDeviceEntity param){
        return iMeetingDeviceService.editMeetingDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询会议端管理", notes = "会议端管理")
    public ReturnInfo getMeetingDevice(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iMeetingDeviceService.getMeetingDevice(param);
    }
}
