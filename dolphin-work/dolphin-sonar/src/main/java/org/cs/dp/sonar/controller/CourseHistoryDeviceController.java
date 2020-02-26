package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CourseHistoryDeviceEntity;
import org.cs.dp.sonar.service.ICourseHistoryDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName CourseHistoryDeviceController
* @Description 日程历史设备
* @Author LiuJT
* @Date 2020-02-25 10:53:08
**/
@RestController
@RequestMapping("courseHistoryDevice")
@Api(tags = "【日程历史设备】")
public class CourseHistoryDeviceController {
    @Autowired
    private ICourseHistoryDeviceService iCourseHistoryDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加日程历史设备", notes = "日程历史设备")
    public ReturnInfo addCourseHistoryDevice(@RequestBody CourseHistoryDeviceEntity param){
        return iCourseHistoryDeviceService.addCourseHistoryDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除日程历史设备", notes = "日程历史设备")
    public ReturnInfo delCourseHistoryDevice(@RequestBody Integer id){
        return iCourseHistoryDeviceService.delCourseHistoryDevice(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改日程历史设备", notes = "日程历史设备")
    public ReturnInfo editCourseHistoryDevice(@RequestBody CourseHistoryDeviceEntity param){
        return iCourseHistoryDeviceService.editCourseHistoryDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程历史设备", notes = "日程历史设备")
    public ReturnInfo getCourseHistoryDevice(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iCourseHistoryDeviceService.getCourseHistoryDevice(param);
    }
}
