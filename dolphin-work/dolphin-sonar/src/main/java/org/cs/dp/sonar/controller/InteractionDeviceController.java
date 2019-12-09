package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.InteractionDeviceEntity;
import org.cs.dp.sonar.service.IInteractionDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName InteractionDeviceController
* @Description 互动-端管理
* @Author LiuJT
* @Date 2019-12-09 03:15:34
**/
@RestController
@RequestMapping("interactionDevice")
@Api(tags = "【互动-端管理】")
public class InteractionDeviceController {
    @Autowired
    private IInteractionDeviceService iInteractionDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加互动-端管理", notes = "互动-端管理")
    public ReturnInfo addInteractionDevice(@RequestBody InteractionDeviceEntity param){
        return iInteractionDeviceService.addInteractionDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除互动-端管理", notes = "互动-端管理")
    public ReturnInfo delInteractionDevice(@RequestBody Integer id){
        return iInteractionDeviceService.delInteractionDevice(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改互动-端管理", notes = "互动-端管理")
    public ReturnInfo editInteractionDevice(@RequestBody InteractionDeviceEntity param){
        return iInteractionDeviceService.editInteractionDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询互动-端管理", notes = "互动-端管理")
    public ReturnInfo getInteractionDevice(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iInteractionDeviceService.getInteractionDevice(param);
    }
}
