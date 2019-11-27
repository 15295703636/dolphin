package org.cs.dp.gateway;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.constant.RedisConstant;
import org.cs.dolphin.common.exception.MessageCode;
import org.cs.dolphin.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName Filter
 * @Description 用户状态拦截器
 * @Author Liujt
 * @Date 2019/11/21 11:00
 **/
@Slf4j
public class Filter implements GlobalFilter, Ordered {
    @Value("${getWay.white.list}")
    private String whiteList;

    @Value("${getWay.prefix}")
    private String prefix;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        //获取请求路径
        final String requestUri = serverHttpRequest.getURI().getPath().substring(prefix.length());
        //白名单过滤
        if (!isStartWith(requestUri)) {
            //token校验,先获取url路径上的token，获取不到，从请求头中获取
            String token = serverHttpRequest.getQueryParams().getFirst("token");
            if (null == token) {
                token = serverHttpRequest.getHeaders().getFirst("token");
            }
            if (StringUtil.isNull(token) || StringUtil.isNull(redisUtil.getStr(RedisConstant.USER_TOKEN_PATH + token))) {
                ServerHttpResponse response = exchange.getResponse();
                byte[] bits = JSON.toJSONString(new ReturnInfo(MessageCode.USER_SESSION, "Token无效请登录!")).getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
            //统一放入请求头中，业务模块从请求头中获取,降低业务数据之间的耦合
            exchange = exchange.mutate().request(serverHttpRequest.mutate().header("token", token).build()).build();
            //刷新token有效时间
            redisUtil.setExpire(RedisConstant.USER_TOKEN_PATH + token, RedisConstant.USER_TOKEN_EXPIRED_TIME);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * URI是否以什么打头
     */
    private boolean isStartWith(String requestUri) {
        boolean flag = false;
        String whites[] = whiteList.split(",");
        for (String s : whites) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return flag;
    }
}
