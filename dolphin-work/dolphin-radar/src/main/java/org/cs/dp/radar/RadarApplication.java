package org.cs.dp.radar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients("org.cs.dp.*.api.feign")
public class RadarApplication {
    public static void main(String[] args) {
        SpringApplication.run(RadarApplication.class, args);
    }
}
