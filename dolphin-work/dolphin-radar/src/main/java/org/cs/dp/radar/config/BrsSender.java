package org.cs.dp.radar.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 动态创建广播队列发送消息
 */
@Component
public class BrsSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 向所有终端广播消息
     * @param message
     */
    public void sendAll(String message){
        this.rabbitmqTemplate.convertAndSend("brs.exchangete.all","", message);
    }

    /**
     * 向所有终端广播消息
     * @param message
     */
    public Object sendAll(String message,String queue){
        return this.rabbitmqTemplate.convertSendAndReceive("brs.exchangete.topic",queue, message);
    }
}
