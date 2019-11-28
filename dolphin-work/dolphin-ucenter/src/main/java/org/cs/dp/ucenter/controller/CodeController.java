package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.CodeBean;
import org.cs.dp.ucenter.service.impl.ICodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @ClassName CodeController
 * @Description 代码表控制层
 * @Author Liujt
 * @Date 2019/11/27 13:59
 **/
@RestController
@RequestMapping("/code")
@Api(tags = "【代码表信息】")
public class CodeController {

    @Autowired
    private ICodeServiceImpl iCodeService;

    @GetMapping("get/{tableName}")
    @ApiOperation(value = "获取代码表：租户类型:customerType", notes = "代码表信息")
    public ReturnInfo getCodeInfo(@PathVariable("tableName") String code) {
        return iCodeService.getCodeInfo(code);
    }

    @PostMapping("edit")
    @ApiOperation(value = "编辑代码表", notes = "代码表信息")
    public ReturnInfo editCodeInfo(@RequestBody CodeBean param) {
        return iCodeService.editCodeInfo(param);
    }

    @PostMapping("add")
    @ApiOperation(value = "添加代码表", notes = "代码表信息")
    public ReturnInfo addCodeInfo(@RequestBody CodeBean param) {
        return iCodeService.addCodeInfo(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除代码表", notes = "代码表信息")
    public ReturnInfo delCodeInfo(@RequestBody CodeBean param) {
        return iCodeService.delCodeInfo(param);
    }

}
