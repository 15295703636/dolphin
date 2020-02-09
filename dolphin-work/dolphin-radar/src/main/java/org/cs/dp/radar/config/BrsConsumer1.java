package org.cs.dp.radar.config;

import lombok.extern.slf4j.Slf4j;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IBrsService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * 接收流媒体普通消息
 */
@Slf4j
@Component
public class BrsConsumer1 {
    @Autowired
    IBrsService iBrsService;

    @RabbitListener(queues = "bsms_keepalive")
    public String receive(Object message) {
        log.info("流媒体消费者1收到消息：{}"+message);
        iBrsService.recMsgFromBrs1(new String(((Message)message).getBody()));
        return "ok";
    }

    @RabbitListener(queues = "abcde123456")
    public String moulds(String message){
        System.out.println("============"+message+"=============");
        return message+"b";
    }

}
