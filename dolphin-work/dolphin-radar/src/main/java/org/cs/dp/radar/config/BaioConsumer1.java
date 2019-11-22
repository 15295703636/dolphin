package org.cs.dp.radar.config;

import org.cs.dp.radar.service.IBaioService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 接收一体化终端普通消息
 */
@Component
public class BaioConsumer1 {
    @Autowired
    IBaioService iBaioService;

    @RabbitListener(queues = "topic.baio1")
    public void receive(String message) {
        System.out.println("消费者1收到消息：{}"+message);
        iBaioService.recMsgFromBaio1(message);
    }
}
