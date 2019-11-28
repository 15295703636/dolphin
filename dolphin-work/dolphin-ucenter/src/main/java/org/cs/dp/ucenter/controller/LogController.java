package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dp.ucenter.domain.GetLogBean;
import org.cs.dp.ucenter.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LogController
 * @Description 日志记录控制层
 * @Author Liujt
 * @Date 2019/11/27 17:06
 **/
@RestController
@RequestMapping("/log")
@Api(tags = "【日志记录】")
public class LogController {

    @Autowired
    private ILogService iLogService;

   /* @PostMapping("add")
    @ApiOperation(value = "添加日志记录", notes = "日志记录")
    public ReturnInfo addLog(@RequestBody LogEntity param){
        return iLogService.addLog(param);
    }*/


    @PostMapping("get")
    @ApiOperation(value = "查询日志记录", notes = "日志记录")
    public ReturnInfo getLog(@RequestBody RequestPage<SplitPageInfo, GetLogBean> param){
        return iLogService.getLog(param);
    }

    @GetMapping("export")
    @ApiOperation(value = "导出日志记录", notes = "日志记录")
    public void exportLog(String moduleName,String startTime,String endTime, HttpServletResponse response) throws Exception {
        iLogService.exportLog( moduleName, startTime,endTime,response);
    }

}
