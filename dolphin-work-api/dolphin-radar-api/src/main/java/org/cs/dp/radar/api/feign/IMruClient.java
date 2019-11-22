package org.cs.dp.radar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = AppConstant.APPLICATION_RADAR_NAME,
        fallback = IMruClientFallBack.class)
public interface IMruClient {
    String API_PREFIX = "/mru";


    @GetMapping(API_PREFIX+"/createUt12")
    ReturnInfo createUt12(@RequestBody Ut12Entity ut12Entity);
}
