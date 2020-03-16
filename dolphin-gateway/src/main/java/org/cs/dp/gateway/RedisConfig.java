package org.cs.dp.gateway;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
