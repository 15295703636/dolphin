package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.RecordBroadcastEntity;
import org.cs.dp.sonar.service.IRecordBroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName RecordBroadcastController
* @Description 录播管理
* @Author LiuJT
* @Date 2019-12-09 03:14:27
**/
@RestController
@RequestMapping("recordBroadcast")
@Api(tags = "【录播管理】")
public class RecordBroadcastController {
    @Autowired
    private IRecordBroadcastService iRecordBroadcastService;

    @PostMapping("add")
    @ApiOperation(value = "添加录播管理", notes = "录播管理")
    public ReturnInfo addRecordBroadcast(@RequestBody RecordBroadcastEntity param){
        return iRecordBroadcastService.addRecordBroadcast(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除录播管理", notes = "录播管理")
    public ReturnInfo delRecordBroadcast(@RequestBody Integer id){
        return iRecordBroadcastService.delRecordBroadcast(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改录播管理", notes = "录播管理")
    public ReturnInfo editRecordBroadcast(@RequestBody RecordBroadcastEntity param){
        return iRecordBroadcastService.editRecordBroadcast(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询录播管理", notes = "录播管理")
    public ReturnInfo getRecordBroadcast(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iRecordBroadcastService.getRecordBroadcast(param);
    }
}
