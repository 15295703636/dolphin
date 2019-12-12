package org.cs.dp.radar.config;

import lombok.extern.slf4j.Slf4j;
import org.cs.dp.radar.service.IBaioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收一体终端心跳
 */
@Slf4j
@Component
public class BaioConsumer2 {
    @Autowired
    IBaioService iBaioService;

    @RabbitListener(queues = "topic.baio2")
    public void receive(String message) {
        log.info("消费者2收到消息：{}"+message);
        iBaioService.recMsgFromBaio2(message);
    }
}
