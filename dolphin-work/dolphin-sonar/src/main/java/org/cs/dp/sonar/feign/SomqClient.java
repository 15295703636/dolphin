package org.cs.dp.sonar.feign;

import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.sonar.api.feign.ISomqClient;
import org.cs.dp.sonar.service.ISoBaioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 供radar服务中接收mq的回调 录播课
 */
@Slf4j
@RestController
public class SomqClient implements ISomqClient {

    @Autowired
    private ISoBaioService iSoBaioService;

    @Override
    @GetMapping(API_PREFIX + "/receivemsg")
    public ReturnInfo receiveMsg(@RequestParam String msg) {
        log.info("录播课入参：{}", msg);
        return iSoBaioService.recordedBroadcastCourse(msg);
    }

    @Override
    public ReturnInfo keepalive(String msg,Integer type) {
        log.info("设备心跳检测入参：{}:{}", type,msg);
        return iSoBaioService.keepalive(msg,type);
    }

}
