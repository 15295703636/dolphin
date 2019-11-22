package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_RADAR_NAME,
        fallback = IBaioClientFallBack.class)
public interface IBaioClient {
    String API_PREFIX = "/baio";


    @GetMapping(API_PREFIX+"/sendmsg")
    ReturnInfo sendmsg(@RequestParam String msg);
}
