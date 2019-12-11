package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.entity.CourseEntity;
import org.cs.dp.sonar.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName CourseController
* @Description 进行中日程管理
* @Author LiuJT
* @Date 2019-12-10 11:21:47
**/
@RestController
@RequestMapping("course")
@Api(tags = "【进行中日程管理】")
public class CourseController {
    @Autowired
    private ICourseService iCourseService;

    @PostMapping("start")
    @ApiOperation(value = "添加(开始)进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo startSchedule(@RequestBody Integer param){
        return iCourseService.startSchedule(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo delCourse(@RequestBody Integer id){
        return iCourseService.delCourse(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo editCourse(@RequestBody CourseEntity param){
        return iCourseService.editCourse(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo getCourse(@RequestBody RequestPage<SplitPageInfo, GetCourseReqBean> param){
        return iCourseService.getCourse(param);
    }

    @PostMapping("liveBro")
    @ApiOperation(value = "直播管理", notes = "日程-端管理")
    public ReturnInfo liveBroadcast(@RequestBody Integer id) {
        return iCourseService.liveBroadcast(id);
    }

    @PostMapping("share")
    @ApiOperation(value = "分享管理", notes = "日程-端管理")
    public ReturnInfo share(@RequestBody Integer id) {
        return iCourseService.share(id);
    }

    @PostMapping("end")
    @ApiOperation(value = "结束管理", notes = "日程-端管理")
    public ReturnInfo end(@RequestBody Integer id) {
        return iCourseService.end(id);
    }
}
