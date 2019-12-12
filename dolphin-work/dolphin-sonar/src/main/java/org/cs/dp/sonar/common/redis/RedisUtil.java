package org.cs.dp.sonar.common.redis;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.constant.ModuleConstant;
import org.cs.dolphin.common.domain.LogEntity;
import org.cs.dolphin.common.utils.ExceptionUtil;
import org.cs.dp.sonar.service.ISoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Description redis工具类，主要判断是集群配置，还是单点配置
 * @Author Liujt
 * @Date 2019/11/26 10:22
 **/
@Slf4j
@Configuration
@EnableCaching
@SuppressWarnings("all")
public class RedisUtil<T> {

    @Resource
    private RedisTemplate redisTemplate;


    @Autowired
    private ISoUserService iSoUserService;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 根据出入类型返回对象
     *
     * @param key
     * @param obj
     * @return
     */
    public Object getObject(String key, Class<T> obj) {
        return JSONObject.parseObject(getStr(key), obj);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String getStr(final String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return (String) operations.get(key);
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存异常：{}", e);
            iSoUserService.addLog(new LogEntity(ModuleConstant.MODULE_UCENTER,
                    "error", "error,", null, null,
                    ExceptionUtil.getStackTrace(e)
            ));
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("写入缓存异常：{}", e);
            iSoUserService.addLog(new LogEntity(ModuleConstant.MODULE_UCENTER,
                    "error", "error,", null, null,
                    ExceptionUtil.getStackTrace(e)
            ));
        }
        return result;
    }

    /**
     * 设置有效时间
     *
     * @param key
     * @param value
     * @return
     */
    public void setExpire(final String key, Long expireTime) {
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

}
