package org.cs.dp.ucenter;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Mapper
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("org.cs.dp.ucenter.mapper")
@EnableFeignClients("org.cs.dp.*.api.feign")
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
        log.error(">>>>>>>>>>>>>>>>Ucenter服务启动完成<<<<<<<<<<<<<<<<<<<<<");
    }
}
