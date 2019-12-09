package org.cs.dp.sonar.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.domain.GetAppSchReqBean;
import org.cs.dp.sonar.service.IAppSchService;
import org.cs.dp.sonar.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @ClassName DeviceController
* @Description 设备/端管理
* @Author LiuJT
* @Date 2019-12-06 05:11:20
**/
@RestController
@RequestMapping("appSch")
@Api(tags = "【预约日程】")
public class AppSchController {
    @Autowired
    private IAppSchService iAppSchService;

    @PostMapping("get")
    @ApiOperation(value = "查询预约日程管理", notes = "设备/端管理")
    public ReturnInfo getAppSch(@RequestBody GetAppSchReqBean param){
        return iAppSchService.getAppSch(param);
    }
}
