package com.example.orderservice; // 定义包名，表示这是订单服务模块

import org.springframework.boot.SpringApplication; // 导入Spring Boot启动类
import org.springframework.boot.autoconfigure.SpringBootApplication; // 导入Spring Boot自动配置注解
import org.springframework.cloud.openfeign.EnableFeignClients; // 导入Feign客户端启用注解

/**
 * 订单服务的主应用程序类
 * 使用@SpringBootApplication注解标记为Spring Boot应用程序
 * 使用@EnableFeignClients注解启用Feign客户端功能，用于服务间通信
 */
@SpringBootApplication // 标记这是一个Spring Boot应用程序，启用自动配置、组件扫描和配置属性支持
@EnableFeignClients // 启用Feign客户端，允许使用声明式REST客户端进行服务间调用
public class OrderServiceApplication {
    /**
     * 应用程序的入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 启动Spring Boot应用程序，使用当前类作为配置类
        SpringApplication.run(OrderServiceApplication.class, args);
    }
} 