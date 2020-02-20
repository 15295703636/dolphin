package org.cs.dp.sonar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = AppConstant.APPLICATION_SONAR_NAME,
        fallback = IServerFallback.class)
public interface IServerClient {
    String API_PREFIX = "/server";

    @PostMapping(API_PREFIX+"/get")
    ReturnInfo getServer(@RequestBody Map<String,Integer> param);
}
