package com.lpx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//该注解用于向 zookeeper consul 作为注册中心时  注册服务
public class Run {
	public static void main(String[] args) {
		SpringApplication.run(Run.class, args);
	}
}
