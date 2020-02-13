package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.CustomerServerEntity;
import org.cs.dp.sonar.service.ICustomerServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
* @ClassName CustomerServerController
* @Description 租户-服务管理
* @Author LiuJT
* @Date 2019-12-11 05:43:31
**/
@RestController
@RequestMapping("customerServer")
@Api(tags = "【租户-服务管理】")
public class CustomerServerController {
    @Autowired
    private ICustomerServerService iCustomerServerService;

    @PostMapping("add")
    @ApiOperation(value = "添加租户-服务管理", notes = "租户-服务管理")
    public ReturnInfo addCustomerServer(@RequestBody CustomerServerEntity param){
        return iCustomerServerService.addCustomerServer(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除租户-服务管理", notes = "租户-服务管理")
    public ReturnInfo delCustomerServer(@RequestBody Map<String ,Integer> param){
        return iCustomerServerService.delCustomerServer(param.get("server_id"));
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改租户-服务管理", notes = "租户-服务管理")
    public ReturnInfo editCustomerServer(@RequestBody CustomerServerEntity param){
        return iCustomerServerService.editCustomerServer(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询租户-服务管理", notes = "租户-服务管理")
    public ReturnInfo getCustomerServer(@RequestBody RequestPage<SplitPageInfo, Integer> param){
        return iCustomerServerService.getCustomerServer(param);
    }
}
