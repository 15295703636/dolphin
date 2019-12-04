package org.cs.dp.radar.config;

import lombok.extern.slf4j.Slf4j;
import org.cs.dp.radar.service.IBaioService;
import org.cs.dp.radar.service.IBrsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收流媒体普通消息
 */
@Slf4j
@Component
public class BrsConsumer1 {
    @Autowired
    IBrsService iBrsService;

    @RabbitListener(queues = "topic.brs1")
    public void receive(String message) {
        log.info("流媒体消费者1收到消息：{}"+message);
        iBrsService.recMsgFromBrs1(message);
    }
}
