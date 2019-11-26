package org.cs.dp.ucenter.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.cs.dp.ucenter.common.RedisSimCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description redis配置序列化
 * @Author Liujt
 * @Date 2019/11/25 15:12
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisConfig {
    private String nodes;
    private int maxRedirects;
    private int timeOut;
    private int soTimeOut;
    private int maxAttempts;

}
