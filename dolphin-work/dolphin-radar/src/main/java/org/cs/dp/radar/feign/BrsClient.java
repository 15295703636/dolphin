package org.cs.dp.radar.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.radar.api.feign.IBrsClient;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IBrsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流媒体接口调用
 */
@RestController
public class BrsClient implements IBrsClient {

    @Autowired
    IBrsService iBrsService;

    @Override
    @GetMapping(API_PREFIX+"/sendmsg")
    public ReturnInfo sendmsg(@RequestParam String msg,@RequestParam String method) {
        System.out.println("brs="+msg);
        return iBrsService.sendmsg(msg,method);
    }

    @Override
    @GetMapping(API_PREFIX+"/sendmsgtopic")
    public ReturnInfo sendmsgtopic(@RequestParam String msg,@RequestParam String method,@RequestParam String queue) {
        return iBrsService.sendmsgtopic(msg,method,queue);
    }
}
