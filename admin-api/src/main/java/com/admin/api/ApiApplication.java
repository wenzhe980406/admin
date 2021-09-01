package com.admin.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 接口应用启动类
 * @author 86
 * @date 2021/08/31 21:09
 */
@SpringBootApplication
@EnableFeignClients("com.admin")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class);
    }
}
