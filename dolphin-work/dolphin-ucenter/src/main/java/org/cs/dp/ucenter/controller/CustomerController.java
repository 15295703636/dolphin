package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName CustomerController
* @Description 租户管理
* @Author Liujt
* @Date 2019-12-05 04:27:19
**/
@RestController
@RequestMapping("customer")
@Api(tags = "【租户管理】")
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("add")
    @ApiOperation(value = "添加租户管理", notes = "租户管理")
    public ReturnInfo addCustomer(@RequestBody CustomerEntity param){
        return iCustomerService.addCustomer(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除租户管理", notes = "租户管理")
    public ReturnInfo delCustomer(@RequestBody Integer id){
        return iCustomerService.delCustomer(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改租户管理", notes = "租户管理")
    public ReturnInfo editCustomer(@RequestBody CustomerEntity param){
        return iCustomerService.editCustomer(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询租户管理", notes = "租户管理")
    public ReturnInfo getCustomer(@RequestBody RequestPage<SplitPageInfo, String> param){
        return iCustomerService.getCustomer(param);
    }
}
