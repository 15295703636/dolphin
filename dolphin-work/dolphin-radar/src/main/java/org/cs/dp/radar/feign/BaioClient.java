package org.cs.dp.radar.feign;

import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.api.entity.Ut12Entity;
import org.cs.dp.radar.api.feign.IBaioClient;
import org.cs.dp.radar.api.feign.IMruClient;
import org.cs.dp.radar.config.BaioSender;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IMruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.cs.dp.radar.api.feign.IBaioClient.API_PREFIX;

/**
 * 终端接口调用
 */
@RestController
public class BaioClient implements IBaioClient {

    @Autowired
    IBaioService iBaioService;

    @Override
    @GetMapping(API_PREFIX+"/sendmsg")
    public ReturnInfo sendmsg(@RequestParam String msg) {
        return iBaioService.sendmsg(msg);
    }
}
