package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_RADAR_NAME,
        fallback = IBaioClientFallBack.class)
public interface IBrsClient {
    String API_PREFIX = "/brs";


    @GetMapping(API_PREFIX+"/sendmsg")
    ReturnInfo sendmsg(@RequestParam String msg, @RequestParam String method);
    @GetMapping(API_PREFIX+"/sendmsgtopic")
    ReturnInfo sendmsgtopic(@RequestParam String msg, @RequestParam String method,@RequestParam String queue);

}
