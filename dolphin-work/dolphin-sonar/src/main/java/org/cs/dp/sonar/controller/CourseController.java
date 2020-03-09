package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.sonar.domain.CourseEndReqBean;
import org.cs.dp.sonar.domain.CourseGetByIdResBean;
import org.cs.dp.sonar.domain.GetCourseReqBean;
import org.cs.dp.sonar.domain.GetDeviceByNameStateReqBean;
import org.cs.dp.sonar.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @PostMapping("del")
    @ApiOperation(value = "删除进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo delCourse(@RequestBody Map param) {
        return new ReturnInfo();//iCourseService.delCourse(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改进行中日程管理", notes = "进行中日程管理")
    public ReturnInfo editCourse(@RequestBody Map param) {
        return new ReturnInfo();//iCourseService.editCourse(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询进行中日程列表", notes = "进行中日程管理")
    public ReturnInfo getCourse(@RequestBody RequestPage<SplitPageInfo, GetCourseReqBean> param) {
        return iCourseService.getCourse(param);
    }

    @PostMapping("getById")
    @ApiOperation(value = "查询日程ID查询教学列表数据", notes = "进行中日程管理")
    public ReturnInfo<CourseGetByIdResBean> getById(@RequestBody Map<String, Integer> param) {
        return iCourseService.getById(param.get("id"));
    }

    @PostMapping("getDeviceByNameState")
    @ApiOperation(value = "根据端名称，状态筛选", notes = "进行中日程管理")
    public ReturnInfo<CourseGetByIdResBean> getDeviceByNameState(@RequestBody GetDeviceByNameStateReqBean param) {
        return iCourseService.getDeviceByNameState(param);
    }

    /*@PostMapping("liveBro")
    @ApiOperation(value = "直播管理", notes = "日程-端管理")
    public ReturnInfo liveBroadcast(@RequestBody Map id) {
        return new ReturnInfo();//iCourseService.liveBroadcast(id);
    }*/

    @PostMapping("share")
    @ApiOperation(value = "分享管理", notes = "日程-端管理")
    public ReturnInfo share(@RequestBody Map id) {
        return new ReturnInfo();//iCourseService.share(id);
    }

    @PostMapping("end")
    @ApiOperation(value = "结束管理", notes = "日程-端管理")
    public ReturnInfo end(@RequestBody CourseEndReqBean param) throws BaseException {
        return iCourseService.end(param);
    }
}
