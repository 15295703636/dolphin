package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.domain.CustomerEntity;
import org.cs.dp.ucenter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName CustomerController
 * @Description 租户信息
 * @Author Liujt
 * @Date 2019/11/21 19:44
 **/
@RestController
@RequestMapping("/customer")
@Api(tags = "【租户信息】")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;

    @PostMapping("add")
    @ApiOperation(value = "添加租户信息", notes = "租户信息")
    public ReturnInfo addCustomer(@RequestBody CustomerEntity param) {
        return iCustomerService.addCustomer(param);
    }

    @PostMapping("edit")
    @ApiOperation(value = "添加租户信息", notes = "租户信息")
    public ReturnInfo editCustomer(@RequestBody CustomerEntity param) {
        return iCustomerService.editCustomer(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除租户信息", notes = "租户信息")
    public ReturnInfo delCustomer(@RequestBody int id) {
        return iCustomerService.delCustomer(id);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询所有租户信息", notes = "租户信息")
    public ReturnInfo getCustomer(@RequestBody RequestPage<SplitPageInfo,CustomerEntity> param) {
        return iCustomerService.getCustomer(param);
    }

}
