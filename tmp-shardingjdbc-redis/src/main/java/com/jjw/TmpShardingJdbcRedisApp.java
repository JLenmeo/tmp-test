package com.jjw;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@MapperScan("com.jjw.mapper")
public class TmpShardingJdbcRedisApp {

    public static void main(String[] args) {

        SpringApplication.run(TmpShardingJdbcRedisApp.class,args);

        System.out.println("============启动成功============");

    }

}
