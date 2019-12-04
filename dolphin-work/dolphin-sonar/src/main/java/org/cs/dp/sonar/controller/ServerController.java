package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.ServerEntity;
import org.cs.dp.sonar.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerController
 * @Description 服务管理
 * @Author Liujt
 * @Date 2019-51-29 11:51:51
 **/
@RestController
@RequestMapping("server")
@Api(tags = "服务管理")
public class ServerController {
    @Autowired
    private IServerService iServerService;

    @PostMapping("add")
    @ApiOperation(value = "添加服务管理", notes = "服务管理")
    public ReturnInfo addServer(@RequestBody ServerEntity param){
        return iServerService.addServer(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除服务管理", notes = "服务管理")
    public ReturnInfo delServer(@RequestBody Integer id){
        return iServerService.delServer(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "编辑服务管理", notes = "服务管理")
    public ReturnInfo editServer(@RequestBody ServerEntity param){
        return iServerService.editServer(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询服务管理", notes = "服务管理")
    public ReturnInfo getServer(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iServerService.getServer(param);
    }
}
