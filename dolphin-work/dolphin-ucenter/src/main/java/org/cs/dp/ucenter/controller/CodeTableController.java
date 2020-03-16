package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.CodeTableEntity;
import org.cs.dp.ucenter.service.ICodeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @ClassName CodeTableController
* @Description 代码表
* @Author Liujt
* @Date 2019-12-09 09:36:10
**/
@RestController
@RequestMapping("codeTable")
@Api(tags = "【代码表】")
public class CodeTableController {
    @Autowired
    private ICodeTableService iCodeTableService;

    @PostMapping("add")
    @ApiOperation(value = "添加代码表", notes = "代码表")
    public ReturnInfo addCodeTable(@RequestBody CodeTableEntity param){
        return iCodeTableService.addCodeTable(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除代码表", notes = "代码表")
    public ReturnInfo delCodeTable(@RequestBody String id){
        return iCodeTableService.delCodeTable(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改代码表", notes = "代码表")
    public ReturnInfo editCodeTable(@RequestBody CodeTableEntity param){
        return iCodeTableService.editCodeTable(param);
    }

    @GetMapping("get")
    @ApiOperation(value = "查询代码表", notes = "代码表")
    public ReturnInfo getCodeTable(String explain){
        return iCodeTableService.getCodeTable(explain);
    }
}
