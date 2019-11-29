package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.sonar.domain.entity.DeviceEntity;
import org.cs.dp.sonar.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName DeviceController
 * @Description 设备管理
 * @Author Liujt
 * @Date 2019-11-29 01:02:05
 **/
@RestController
@RequestMapping("device")
@Api(tags = "设备管理")
public class DeviceController {
    @Autowired
    private IDeviceService iDeviceService;

    @PostMapping("add")
    @ApiOperation(value = "添加设备管理", notes = "设备管理")
    public ReturnInfo addDevice(@RequestBody DeviceEntity param){
        return iDeviceService.addDevice(param);
    }

    @PostMapping("del")
    @ApiOperation(value = "删除设备管理", notes = "设备管理")
    public ReturnInfo delDevice(@RequestBody Integer id){
        return iDeviceService.delDevice(id);
    }

    @PostMapping("edit")
    @ApiOperation(value = "修改设备管理", notes = "设备管理")
    public ReturnInfo editDevice(@RequestBody DeviceEntity param){
        return iDeviceService.editDevice(param);
    }

    @PostMapping("get")
    @ApiOperation(value = "查询设备管理", notes = "设备管理")
    public ReturnInfo getDevice(@RequestBody RequestPage<SplitPageInfo, Object> param){
        return iDeviceService.getDevice(param);
    }
}
