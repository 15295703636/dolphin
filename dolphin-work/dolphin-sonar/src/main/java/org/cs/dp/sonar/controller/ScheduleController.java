package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetScheduleBean;
import org.cs.dp.sonar.domain.ScheduleArrayBean;
import org.cs.dp.sonar.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ScheduleController
 * @Description 日程管理
 * @Author LiuJT
 * @Date 2019-12-09 05:55:10
 **/
@RestController
@RequestMapping("schedule")
@Api(tags = "【日程配置管理】")
public class ScheduleController {
    @Autowired
    private IScheduleService iScheduleService;

    @PostMapping("add")
    @ApiOperation(value = "添加日程配置管理", notes = "日程配置管理")
    public ReturnInfo addSchedule(@RequestBody ScheduleArrayBean param) {
        return iScheduleService.addSchedule(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除日程配置管理", notes = "日程配置管理")
    public ReturnInfo delSchedule(@RequestBody Integer id) {
        return iScheduleService.delSchedule(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改日程配置管理", notes = "日程配置管理")
    public ReturnInfo editSchedule(@RequestBody ScheduleArrayBean param) {
        return iScheduleService.editSchedule(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程配置管理", notes = "日程配置管理")
    public ReturnInfo getSchedule(@RequestBody RequestPage<SplitPageInfo, GetScheduleBean> param) {
        return iScheduleService.getSchedule(param);
    }

}
