package org.cs.dp.ucenter.common;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @ClassName RedisCondition
 * @Description redis客户端还是集群部署判断
 * @Author Liujt
 * @Date 2019/11/26 10:50
 **/
@Service
public class RedisClusterCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        if ("1".equals(conditionContext.getEnvironment().getProperty("spring.redis.isCluster"))) {
            return true;
        }
        return false;
    }
}
