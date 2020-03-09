package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.UserApplyEntity;
import org.cs.dp.ucenter.service.IUserApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @ClassName UserApplyController
* @Description 账号申请
* @Author Liujt
* @Date 2019-12-06 03:28:30
**/
@RestController
@RequestMapping("userApply")
@Api(tags = "【账号申请】")
public class UserApplyController {
    @Autowired
    private IUserApplyService iUserApplyService;

    @PostMapping("add")
    @ApiOperation(value = "添加账号申请", notes = "账号申请")
    public ReturnInfo addUserApply(@RequestBody UserApplyEntity param){
        return iUserApplyService.addUserApply(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除账号申请", notes = "账号申请")
    public ReturnInfo delUserApply(@RequestBody Integer id){
        return iUserApplyService.delUserApply(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改(同意，拒绝)账号申请", notes = "账号申请")
    public ReturnInfo editUserApply(@RequestBody UserApplyEntity param){
        return iUserApplyService.editUserApply(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询账号申请", notes = "账号申请")
    public ReturnInfo<List<UserApplyEntity>> getUserApply(@RequestBody RequestPage<SplitPageInfo, Integer> param){
        return iUserApplyService.getUserApply(param);
    }
}
