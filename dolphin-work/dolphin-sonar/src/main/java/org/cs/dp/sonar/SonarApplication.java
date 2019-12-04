package org.cs.dp.sonar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 业务
 */
@EnableAsync
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("org.cs.dp.*.api.feign")
@MapperScan("org.cs.dp.sonar.mapper")
public class SonarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SonarApplication.class, args);
    }
}
