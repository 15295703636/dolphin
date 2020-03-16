package org.cs.dp.gateway;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.cs.dolphin.common.base.ReturnInfo;
import org.cs.dolphin.common.exception.MessageCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SuppressWarnings("ALL")
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient  // 启用服务注册和发现
@RestController  // 提供一个简单的降级页面
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
        log.error("-----------------------GatewayApplication启动成功-----------------------");
    }

    /**
     * @Title: fallback
     * @Description: 一个简单的降级页面
     * @return
     */
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        // Mono是一个Reactive stream，对外输出一个“fallback”字符串。
        ReturnInfo returnInfo = new ReturnInfo(MessageCode.SERVER_EXCEPTION,"后端服务维护,请等待!");
        return Mono.just(JSONObject.toJSONString(returnInfo));
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 配制一个路由,把 http://网关地址:网关端口/demo/ 下的请求路由到 demo-service 微服务中
                .route(p -> p
                        .path("/ucenter/**")//http://localhost:9009/api/f/feign
                        .filters(f -> f
                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
                                        .setName("mycmd")   // 熔断器的名字
                                        .setFallbackUri("forward:/fallback")
                                )// 熔断到 /fallback, 就是上面配制的那个
                                .stripPrefix(1))  //路由会去除/api/v1
                        .uri("lb://dolphin-ucenter"))// 将请求路由到指定目标, lb开头是注册中心中的服务, http/https 开头你懂的
                .route(p -> p
                        .path("/sonar/**")
                        .filters(f -> f
                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
                                        .setName("mycmd1")   // 熔断器的名字
                                        .setFallbackUri("forward:/fallback"))  // 熔断到 /fallback, 就是上面配制的那个
                                .stripPrefix(1))  //路由会去除/api/v1
                        .uri("lb://dolphin-sonar"))//http://localhost:9009/api/v1/helloNacos
                .route(p -> p.path("/socketServer/**")
                        .filters(f -> f
                                .hystrix(config -> config   // 对path()指定的请求使用熔断器
                                        .setName("mycmd1")   // 熔断器的名字
                                        .setFallbackUri("forward:/fallback"))  // 熔断到 /fallback, 就是上面配制的那个
                                .stripPrefix(1))  //路由会去除/api/v1
                        .uri("lb:ws://dolphin-sonar"))
                .route(r -> r.path("/user/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://blade-consumer"))//http://localhost:9009/user/test
                .build();
    }

    @Bean
    public Filter tokenFilter() {
        return new Filter();
    }
}






