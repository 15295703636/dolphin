package org.cs.dp.sonar.api.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.utils.AppConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = AppConstant.APPLICATION_SONAR_NAME,
        fallback = ISomqClientFallback.class)
public interface ISomqClient {
    String API_PREFIX = "/somq";

    @GetMapping(API_PREFIX + "/receivemsg")
    ReturnInfo receiveMsg(@RequestParam(name = "msg") String msg);

    /**
     * @param msg
     * @param type 1终端 2流媒体
     * @return
     */
    @GetMapping(API_PREFIX + "/keepalive")
    ReturnInfo keepalive(@RequestParam(name = "msg") String msg, @RequestParam(name = "type") Integer type);
}
