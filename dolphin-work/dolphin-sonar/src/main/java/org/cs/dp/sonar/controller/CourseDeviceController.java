package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.*;
import org.cs.dp.sonar.service.ICourseDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CourseDeviceController
 * @Description 日程-端管理
 * @Author LiuJT
 * @Date 2019-12-10 01:11:55
 **/
@RestController
@RequestMapping("courseDevice")
@Api(tags = "【日程的-端管理】")
public class CourseDeviceController {
    @Autowired
    private ICourseDeviceService iCourseDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加日程-端管理", notes = "日程-端管理")
    public ReturnInfo addCourseDevice(@RequestBody CourseDeviceAddReqBean param) {
        return iCourseDeviceService.addCourseDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除日程-端管理", notes = "日程-端管理")
    public ReturnInfo delCourseDevice(@RequestBody CourseDeviceReqBean param) {
        return iCourseDeviceService.delCourseDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程-端管理", notes = "日程-端管理")
    public ReturnInfo getCourseDevice(@RequestBody Map<String, Integer> param) {
        return iCourseDeviceService.getCourseDevice(param.get("id"));
    }

    @PostMapping("mute")
    @ApiOperation(value = "静音", notes = "日程-端管理")
    public ReturnInfo mute(@RequestBody CourseDeviceMuteReqBean param) {
        return iCourseDeviceService.mute(param);
    }

    @PostMapping("connect")
    @ApiOperation(value = "连接", notes = "日程-端管理")
    public ReturnInfo connect(@RequestBody CourseDeviceReqBean param) {
        return iCourseDeviceService.connect(param);
    }

    @PostMapping("setLecturer")
    @ApiOperation(value = "设置主讲", notes = "日程-端管理")
    public ReturnInfo setLecturer(@RequestBody CourseDeviceSetLecturerReqBean param) {
        return iCourseDeviceService.setLecturer(param);
    }

    @PostMapping("setDiscMode")
    @ApiOperation(value = "设置讨论模式", notes = "日程-端管理")
    public ReturnInfo setDiscMode(@RequestBody CourseDeviceSpeakReqBean param) {
        return iCourseDeviceService.setDiscMode(param);
    }

    @PostMapping("speak")
    @ApiOperation(value = "设置发言", notes = "日程-端管理")
    public ReturnInfo speak(@RequestBody CourseDeviceSpeakReqBean param) {
        return iCourseDeviceService.speak(param);
    }

    @PostMapping("baioControl")
    @ApiOperation(value = "录播控制暂停/继续", notes = "日程-端管理")
    public ReturnInfo baioControl(@RequestBody BaioControlReqBean param) {
        return iCourseDeviceService.baioControl(param);
    }


   /* @PostMapping("edit")
    @ApiOperation(value = "修改日程-端管理", notes = "日程-端管理")
    public ReturnInfo editCourseDevice(@RequestBody Map param) {
        return iCourseDeviceService.editCourseDevice(param);
    }*/

    /*@PostMapping("cancelMute")
    @ApiOperation(value = "取消", notes = "日程-端管理")
    public ReturnInfo cancelMute(@RequestBody Map id) {
        return new ReturnInfo();//iCourseDeviceService.cancelMute(id);
    }*/

   /* @PostMapping("startLiveStreaming")
    @ApiOperation(value = "开启直播", notes = "日程-端管理")
    public ReturnInfo startLiveStreaming(@RequestBody LiveStreamingReqBean param){
        return iCourseDeviceService.startLiveStreaming(param.get("ysx_course_id"),"1");
    }

    @PostMapping("stopLiveStreaming")
    @ApiOperation(value = "停止直播", notes = "日程-端管理")
    public ReturnInfo stopLiveStreaming(@RequestBody LiveStreamingReqBean param){
        return iCourseDeviceService.stopLiveStreaming(param.get("ysx_course_id"));
    }*/

    /*@PostMapping("setPeerLayout")
    @ApiOperation(value = "设置分屏模式", notes = "日程-端管理")
    public ReturnInfo setPeerLayout(@RequestBody RestPartyLayoutReqBean param) {
        return iCourseDeviceService.setPeerLayout(param);
    }*/

    /*@PostMapping("sidelines")
    @ApiOperation(value = "旁观", notes = "日程-端管理")
    public ReturnInfo sidelines(@RequestBody Map id) {
        return iCourseDeviceService.sidelines(id);
    }*/
}
