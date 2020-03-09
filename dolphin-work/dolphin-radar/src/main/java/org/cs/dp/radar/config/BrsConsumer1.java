package org.cs.dp.radar.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IBrsService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 接收流媒心跳
 */
@Slf4j
@Component
public class BrsConsumer1 {
    @Autowired
    IBrsService iBrsService;

    @RabbitListener(queues = "bsms_keepalive_queue")
    public String receive(@Payload Message body) {
        log.info("流媒体消费者收到消息：{}", JSONObject.toJSONString(new String(body.getBody())));
        ReturnInfo returnInfo = iBrsService.keepalive(new String(body.getBody()));
        return returnInfo.getReturnData().toString();
    }

}
