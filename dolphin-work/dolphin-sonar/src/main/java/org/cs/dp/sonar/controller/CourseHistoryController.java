package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.GetCourseHistoryReqBean;
import org.cs.dp.sonar.domain.entity.CourseHistoryEntity;
import org.cs.dp.sonar.service.ICourseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName CourseHistoryController
* @Description 日程历史管理
* @Author LiuJT
* @Date 2019-12-10 02:56:38
**/
@RestController
@RequestMapping("courseHistory")
@Api(tags = "【日程历史管理】")
public class CourseHistoryController {
    @Autowired
    private ICourseHistoryService iCourseHistoryService;

    @PostMapping("add")
    @ApiOperation(value = "添加日程历史管理", notes = "日程历史管理")
    public ReturnInfo addCourseHistory(@RequestBody CourseHistoryEntity param){
        return iCourseHistoryService.addCourseHistory(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除日程历史管理", notes = "日程历史管理")
    public ReturnInfo delCourseHistory(@RequestBody Integer id){
        return iCourseHistoryService.delCourseHistory(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改日程历史管理", notes = "日程历史管理")
    public ReturnInfo editCourseHistory(@RequestBody CourseHistoryEntity param){
        return iCourseHistoryService.editCourseHistory(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询日程历史管理", notes = "日程历史管理")
    public ReturnInfo getCourseHistory(@RequestBody RequestPage<SplitPageInfo, GetCourseHistoryReqBean> param){
        return iCourseHistoryService.getCourseHistory(param);
    }
}
