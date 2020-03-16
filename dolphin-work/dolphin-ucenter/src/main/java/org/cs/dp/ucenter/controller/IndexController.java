package org.cs.dp.ucenter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.ucenter.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Author Liujt
 * @Date 2020/3/6 15:22
 **/
@Api(tags = "【首页数据查询】")
@RestController
@RequestMapping(value = "index")
public class IndexController {

    @Autowired
    private IIndexService iIndexService;

    @GetMapping(value = "getUserCount")
    @ApiOperation(value = "统计用户数量", notes = "首页数据查询")
    public ReturnInfo getUserCount() {
        return iIndexService.getUserCount();
    }

}
