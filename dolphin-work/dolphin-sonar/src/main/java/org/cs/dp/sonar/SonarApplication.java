package org.cs.dp.sonar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("org.cs.dp.ucenter.api.feign")
public class SonarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SonarApplication.class, args);
    }
}
