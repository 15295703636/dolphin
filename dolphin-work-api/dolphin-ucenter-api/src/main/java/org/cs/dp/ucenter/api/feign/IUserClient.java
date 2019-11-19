package org.cs.dp.ucenter.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_UCENTER_NAME,
            fallback = IUserClientFallback.class)
public interface IUserClient {
    String API_PREFIX = "/user";

    @GetMapping(API_PREFIX+"/selectUserByOrg")
    ReturnInfo selectUserByOrg(@RequestParam(defaultValue = "1") Integer page,
                               @RequestParam(defaultValue = "10") Integer rows,
                               @RequestParam Integer org_id);
}
