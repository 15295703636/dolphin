package org.cs.dp.ucenter.controller;

import com.github.pagehelper.PageInfo;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @RequestMapping("/getList")
    private ReturnInfo getList(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "2") Integer rows){
        SplitPageInfo pageInfo = new SplitPageInfo();
        pageInfo.setCurrPage(page);
        pageInfo.setPerPageNum(rows);
        PageInfo pInfo = iUserService.selectUserByOrg(pageInfo,null);
        ReturnInfo r = ReturnInfo.ok(pInfo.getList());
        r.setPage(pageInfo);
        return r;
    }

}
