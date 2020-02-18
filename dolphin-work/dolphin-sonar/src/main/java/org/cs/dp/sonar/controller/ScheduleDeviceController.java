package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ScheduleDeviceEntity;
import org.cs.dp.sonar.service.IScheduleDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName ScheduleDeviceController
* @Description 日程-端管理
* @Author LiuJT
* @Date 2020-02-18 11:23:56
**/
@RestController
@RequestMapping("scheduleDevice")
@Api(tags = "【日程-端管理】")
public class ScheduleDeviceController {
    @Autowired
    private IScheduleDeviceService iScheduleDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加日程-端管理", notes = "日程-端管理")
    public ReturnInfo addScheduleDevice(@RequestBody ScheduleDeviceEntity param){
        return iScheduleDeviceService.addScheduleDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除日程-端管理", notes = "日程-端管理")
    public ReturnInfo delScheduleDevice(@RequestBody Integer id){
        return iScheduleDeviceService.delScheduleDevice(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改日程-端管理", notes = "日程-端管理")
    public ReturnInfo editScheduleDevice(@RequestBody ScheduleDeviceEntity param){
        return iScheduleDeviceService.editScheduleDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程-端管理", notes = "日程-端管理")
    public ReturnInfo getScheduleDevice(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iScheduleDeviceService.getScheduleDevice(param);
    }
}
