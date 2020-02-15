package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.VideoDemandEntity;
import org.cs.dp.sonar.service.IVideoDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @ClassName VideoDemandController
* @Description 点播管理
* @Author LiuJT
* @Date 2020-02-13 10:04:14
**/
@RestController
@RequestMapping("videoDemand")
@Api(tags = "【点播管理】")
public class VideoDemandController {
    @Autowired
    private IVideoDemandService iVideoDemandService;

    @PostMapping("add")
    @ApiOperation(value = "添加点播管理", notes = "点播管理")
    public ReturnInfo addVideoDemand(@RequestBody VideoDemandEntity param){
        return iVideoDemandService.addVideoDemand(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除点播管理", notes = "点播管理")
    public ReturnInfo delVideoDemand(@RequestBody List<Integer> ids){
        return iVideoDemandService.delVideoDemand(ids);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改点播管理", notes = "点播管理")
    public ReturnInfo editVideoDemand(@RequestBody VideoDemandEntity param){
        return iVideoDemandService.editVideoDemand(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询点播管理", notes = "点播管理")
    public ReturnInfo getVideoDemand(@RequestBody RequestPage<SplitPageInfo, String> param){
        return iVideoDemandService.getVideoDemand(param);
    }
}
