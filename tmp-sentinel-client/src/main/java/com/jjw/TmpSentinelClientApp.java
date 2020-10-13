package com.jjw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

@EnableAutoConfiguration(exclude = RedisAutoConfiguration.class)
@SpringBootApplication
public class TmpSentinelClientApp {

    public static void main(String[] args) {

        SpringApplication.run(TmpSentinelClientApp.class,args);
        System.out.println("============启动成功============");

    }

}
