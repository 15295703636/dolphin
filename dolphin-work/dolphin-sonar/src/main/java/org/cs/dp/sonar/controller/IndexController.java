package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.CourseGetByIdResBean;
import org.cs.dp.sonar.domain.index.GetClaStaResBean;
import org.cs.dp.sonar.domain.index.IndexCountReqBean;
import org.cs.dp.sonar.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName IndexController
 * @Description 租户首页
 * @Author Liujt
 * @Date 2020/3/6 9:39
 **/
@Api(tags = "【首页数据查询】")
@RestController
@RequestMapping(value = "index")
public class IndexController {

    @Autowired
    private IIndexService iIndexService;

    @GetMapping("getCourse")
    @ApiOperation(value = "获取进行中的日程", notes = "租户首页")
    public ReturnInfo<List<CourseGetByIdResBean>> getCourse(@RequestParam Integer type) {
        return iIndexService.getCourse(type);
    }

    @GetMapping("getSchedule")
    @ApiOperation(value = "获取日程配置信息", notes = "租户首页")
    public ReturnInfo getSchedule(@RequestParam String date) {
        return iIndexService.getSchedule(date);
    }

    @PostMapping("getCourseHistory")
    @ApiOperation(value = "获取教学数据(开课历史)", notes = "租户首页")
    public ReturnInfo getCourseHistory(@RequestBody IndexCountReqBean param) {
        return iIndexService.getCourseHistory(param);
    }

    @GetMapping("getClaSta")
    @ApiOperation(value = "分类汇总统计", notes = "租户首页")
    public ReturnInfo<GetClaStaResBean> getClaSta() {
        return iIndexService.getClaSta();
    }

}
