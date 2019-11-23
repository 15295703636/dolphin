package org.cs.dp.ucenter.feign;

import org.cs.dolphin.common.base.RequestPage;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.base.SplitPageInfo;
import org.cs.dp.ucenter.api.feign.IUserClient;
import org.cs.dp.ucenter.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClient implements IUserClient {

    @Autowired
    private IUserService service;

    @Override
    @GetMapping(API_PREFIX+"/selectUserByOrg")
    public ReturnInfo selectUserByOrg(Integer page, Integer rows,Integer org_id) {
        SplitPageInfo pageInfo = new SplitPageInfo();
        pageInfo.setCurrPage(page);
        pageInfo.setPerPageNum(rows);
        System.out.println("org_id===="+org_id);
        return service.getUsersByOrgId(new RequestPage<>(pageInfo,org_id));
    }
}
