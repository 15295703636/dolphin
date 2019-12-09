package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.AddInteractionBean;
import org.cs.dp.sonar.domain.entity.InteractionEntity;
import org.cs.dp.sonar.service.IInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName InteractionController
* @Description 互动管理
* @Author LiuJT
* @Date 2019-12-09 03:11:48
**/
@RestController
@RequestMapping("interaction")
@Api(tags = "【互动管理】")
public class InteractionController {
    @Autowired
    private IInteractionService iInteractionService;

    @PostMapping("add")
    @ApiOperation(value = "添加互动管理", notes = "互动管理")
    public ReturnInfo addInteraction(@RequestBody AddInteractionBean param){
        return iInteractionService.addInteraction(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除互动管理", notes = "互动管理")
    public ReturnInfo delInteraction(@RequestBody Integer id){
        return iInteractionService.delInteraction(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改互动管理", notes = "互动管理")
    public ReturnInfo editInteraction(@RequestBody InteractionEntity param){
        return iInteractionService.editInteraction(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询互动管理", notes = "互动管理")
    public ReturnInfo getInteraction(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iInteractionService.getInteraction(param);
    }
}
