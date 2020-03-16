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

/**
 * 接收一体终端心跳
 */
@Slf4j
@Component
public class BaioConsumer2 {
    @Autowired
    IBaioService iBaioService;

    @SendTo
    @RabbitListener(queues = "baio_terminal_keepalive")
    public String receive(@Payload Message body) {
        log.info("终端心跳收到消息：{}", JSONObject.toJSONString(new String(body.getBody())));
        String resData = null;
        try {
            ReturnInfo returnInfo = iBaioService.keepalive(new String(body.getBody()));
            log.info("心跳业务处理返回：{}", JSON.toJSONString(returnInfo));
            if (MessageCode.COMMON_SUCCEED_FLAG == returnInfo.getReturnCode()) {
                resData = String.valueOf(returnInfo.getReturnData());
            }
        } catch (Exception e) {
            log.error("终端心跳消息处理异常", e);
            resData = "{\"code\":5000}";
        }
        log.info("终端心跳返回消息：{}", resData);
        return resData;
    }
}
