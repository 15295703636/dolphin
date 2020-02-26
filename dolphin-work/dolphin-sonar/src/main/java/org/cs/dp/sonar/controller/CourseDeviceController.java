package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.CourseDeviceAddReqBean;
import org.cs.dp.sonar.domain.CourseDeviceReqBean;
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

    @PostMapping("edit")
    @ApiOperation(value = "修改日程-端管理", notes = "日程-端管理")
    public ReturnInfo editCourseDevice(@RequestBody Map param) {
        return new ReturnInfo();//iCourseDeviceService.editCourseDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程-端管理", notes = "日程-端管理")
    public ReturnInfo getCourseDevice(@RequestBody Map<String,Integer> param) {
        return iCourseDeviceService.getCourseDevice(param.get("id"));
    }

    @PostMapping("mute")
    @ApiOperation(value = "静音", notes = "日程-端管理")
    public ReturnInfo mute(@RequestBody Map id) {
        return new ReturnInfo();//return iCourseDeviceService.mute(id);
    }

    @PostMapping("cancelMute")
    @ApiOperation(value = "取消", notes = "日程-端管理")
    public ReturnInfo cancelMute(@RequestBody Map id) {
        return new ReturnInfo();//iCourseDeviceService.cancelMute(id);
    }

    @PostMapping("connect")
    @ApiOperation(value = "连接", notes = "日程-端管理")
    public ReturnInfo connect(@RequestBody CourseDeviceReqBean param) {
        return iCourseDeviceService.connect(param);
    }

    @PostMapping("sidelines")
    @ApiOperation(value = "旁观", notes = "日程-端管理")
    public ReturnInfo sidelines(@RequestBody Map id) {
        return null;//iCourseDeviceService.sidelines(id);
    }

}
