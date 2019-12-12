package org.cs.dp.sonar.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @author Liujt 描述：订阅监听类
 */
@Slf4j
@Component
public class SubscribeListener implements MessageListener {

    private Session session;
    
    /**
	 * 订阅接收发布者的消息
	 */
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String msg = new String(message.getBody());
		log.info(new String(pattern) + "主题发布：" + msg);
        if(null!=session){
        	try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				log.error("消息发送失败：",e);
			}
        }else {
			log.error("--------未发现客户端session--------");
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
    
}
