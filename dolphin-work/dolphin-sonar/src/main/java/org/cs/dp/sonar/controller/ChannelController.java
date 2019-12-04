package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ChannelEntity;
import org.cs.dp.sonar.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName ChannelController
* @Description 频道分类
* @Author LiuJT
* @Date 2019-12-02 05:44:59
**/
@RestController
@RequestMapping("channel")
@Api(tags = "频道分类")
public class ChannelController {
    @Autowired
    private IChannelService iChannelService;

    @PostMapping("add")
    @ApiOperation(value = "添加频道分类", notes = "频道分类")
    public ReturnInfo addChannel(@RequestBody ChannelEntity param){
        return iChannelService.addChannel(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除频道分类", notes = "频道分类")
    public ReturnInfo delChannel(@RequestBody Integer id){
        return iChannelService.delChannel(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改频道分类", notes = "频道分类")
    public ReturnInfo editChannel(@RequestBody ChannelEntity param){
        return iChannelService.editChannel(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询频道分类", notes = "频道分类")
    public ReturnInfo getChannel(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iChannelService.getChannel(param);
    }
}
