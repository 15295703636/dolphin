package org.cs.dp.radar.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dp.radar.service.IBaioService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 * 接收一体化终端普通消息
 */
@Slf4j
@Component
public class BaioConsumer1 {
    @Autowired
    IBaioService iBaioService;

    @SendTo
    @RabbitListener(queues = "baio_recorded_broadcast_course")
    public String receive(@Payload Message body) {
        log.info("端调用监听：{}", JSONObject.toJSONString(new String(body.getBody())));
        try {
            ReturnInfo<String> res = iBaioService.recMsgFromBaio1(new String(body.getBody()));
            log.info("端调用监听处理返回：{}", JSON.toJSONString(res));
            if (MessageCode.COMMON_SUCCEED_FLAG == res.getReturnCode()) {
                return String.valueOf(res.getReturnData());
            }
            return String.format("{\"code\":5000}");
        } catch (Exception e) {
            log.error("端调用监听处理异常", e);
            return String.format("{\"code\":5000}");
        }
    }
}
