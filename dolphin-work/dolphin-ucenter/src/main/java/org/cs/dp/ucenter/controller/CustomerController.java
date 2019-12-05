package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ParamValid;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dolphin.common.exception.BaseException;
import org.cs.dp.ucenter.domain.AddCustomerBean;
import org.cs.dp.ucenter.domain.EditStatusBean;
import org.cs.dp.ucenter.domain.entity.CustomerEntity;
import org.cs.dp.ucenter.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @ParamValid
    @PostMapping("add")
    @ApiOperation(value = "添加租户管理", notes = "租户管理")
    public ReturnInfo addCustomer(@Valid @RequestBody AddCustomerBean param, BindingResult result) throws BaseException {
        return iCustomerService.addCustomer(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除租户管理", notes = "租户管理")
    public ReturnInfo delCustomer(@RequestBody List<Integer> ids) {
        return iCustomerService.delCustomer(ids);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改租户管理", notes = "租户管理")
    public ReturnInfo editCustomer(@RequestBody CustomerEntity param) {
        return iCustomerService.editCustomer(param);
    }

    @PostMapping("getByName")
    @ApiOperation(value = "根据用户名称模糊分页查询租户管理", notes = "租户管理")
    public ReturnInfo getCustomer(@RequestBody RequestPage<SplitPageInfo, String> param) {
        return iCustomerService.getCustomer(param);
    }

    @PostMapping("getByManageId")
    @ApiOperation(value = "客户代表Id查询租户管理", notes = "租户管理")
    public ReturnInfo getCustomerByManageId(@RequestBody Integer manageId) {
        return iCustomerService.getCustomerByManageId(manageId);
    }

    @PostMapping("editStatus")
    @ApiOperation(value = "用户状态更改", notes = "租户管理")
    public ReturnInfo editStatus(@RequestBody EditStatusBean param) {
        return iCustomerService.editStatus(param);
    }

}
