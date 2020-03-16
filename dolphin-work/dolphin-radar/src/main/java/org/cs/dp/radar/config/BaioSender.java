package org.cs.dp.radar.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/**
 * 动态创建广播队列发送消息
 */
@Slf4j
@Component
public class BaioSender {

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    /**
     * 向所有终端广播消息
     *
     * @param message
     */
    public ReturnInfo sendAll(String message, String queue) {
        log.info("向终端发送消息:{}", message);
        Object o = this.rabbitmqTemplate.convertSendAndReceive(queue, message);
        log.info("终端返回消息:{}",JSONObject.toJSONString(o) );
        if (o == null) {
            return new ReturnInfo(MessageCode.Fail_Peer_Cannot_Call, "终端未返回消息");
        }
        try {
            String resStr = JSONObject.toJSONString(o);
            JSONObject jsonObject = JSONObject.parseObject(new String(Base64Utils.decodeFromString(resStr.replaceAll("\"",""))));
            if (MessageCode.COMMON_SUCCEED_FLAG != (Integer) jsonObject.get("code")) {
                return new ReturnInfo(MessageCode.Fail_Peer_Cannot_Call, resStr);
            }
        } catch (Exception e) {
            log.error("终端返回消息处理异常", e);
        }
        return new ReturnInfo();
    }
}
