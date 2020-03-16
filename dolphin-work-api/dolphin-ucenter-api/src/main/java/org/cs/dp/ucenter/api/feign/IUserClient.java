package org.cs.dp.ucenter.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dolphin.common.utils.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_UCENTER_NAME,
        fallback = IUserClientFallback.class)
public interface IUserClient {
    String API_PREFIX = "/user";
    String API_PREFIX_LOG = "/log";

    @GetMapping(API_PREFIX + "/selectUserByOrg")
    ReturnInfo selectUserByOrg(@RequestParam(defaultValue = "1", name = "page") Integer page,
                               @RequestParam(defaultValue = "10", name = "rows") Integer rows,
                               @RequestParam(name = "org_id") Integer org_id);

    @PostMapping(API_PREFIX_LOG + "/add")
    ReturnInfo allLog(@RequestBody LogEntity param);
}
