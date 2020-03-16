package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.domain.entity.CodeEntity;
import org.cs.dp.ucenter.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @ClassName CodeController
* @Description 代码信息维护
* @Author Liujt
* @Date 2019-12-09 09:37:29
**/
@RestController
@RequestMapping("code")
@Api(tags = "【代码信息维护】")
public class CodeController {
    @Autowired
    private ICodeService iCodeService;

    @PostMapping("add")
    @ApiOperation(value = "添加代码信息维护", notes = "代码信息维护")
    public ReturnInfo addCode(@RequestBody CodeEntity param){
        return iCodeService.addCode(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除代码信息维护", notes = "代码信息维护")
    public ReturnInfo delCode(@RequestBody Integer id){
        return iCodeService.delCode(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改代码信息维护", notes = "代码信息维护")
    public ReturnInfo editCode(@RequestBody CodeEntity param){
        return iCodeService.editCode(param);
    }

    @GetMapping("get")
    @ApiOperation(value = "查询代码信息维护", notes = "代码信息维护")
    public ReturnInfo getCode(@RequestParam(name = "tableName") String tableName){
        return iCodeService.getCode(tableName);
    }
}
