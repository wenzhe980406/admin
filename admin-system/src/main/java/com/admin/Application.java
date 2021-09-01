package com.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 启动类
 * @author Eva.Caesar Liu
 * @date 2021/08/31 21:16
 */
@Slf4j
@SpringBootApplication
@MapperScan("com.admin.dao")
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class);
        context.getEnvironment();
    }
}
