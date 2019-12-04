package org.cs.dp.sonar.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 七脉 描述：发布service
 */
@Component
public class PublishService {
	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	/**
	 * @author 七脉 描述：发布方法
	 * @param channel 消息发布订阅 主题
	 * @param message 消息信息
	 */
	public void publish(String channel, Object message) {
		// 该方法封装的 connection.publish(rawChannel, rawMessage);
		redisTemplate.convertAndSend(channel, message);
	}
}
