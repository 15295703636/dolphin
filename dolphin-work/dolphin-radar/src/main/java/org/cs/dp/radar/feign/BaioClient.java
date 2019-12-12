package org.cs.dp.radar.feign;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.radar.service.IBaioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 终端接口调用
 */
@Slf4j
@RestController
public class BaioClient implements IBaioClient {

    @Autowired
    IBaioService iBaioService;

    @Override
    @GetMapping(API_PREFIX+"/sendmsg")
    public ReturnInfo sendmsg(@RequestParam String msg,@RequestParam String method) {
        log.info("baio="+msg);
        return iBaioService.sendmsg(msg,method);
    }
}
