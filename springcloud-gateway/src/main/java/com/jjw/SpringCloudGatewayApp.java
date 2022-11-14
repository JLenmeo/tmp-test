package com.jjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: Jiang JiaWei
 * @date: 2022/11/11 15:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApp.class, args);
    }

}
