package com.lpx.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//将 RestTemplate  注册到容器
@Configuration
public class ApplicationConfig {

	//	注入对象
	@Bean
//	@LoadBalanced //负载均衡 默认轮训算法
	public  RestTemplate getRestTemplate() {
	
		return new RestTemplate();
		
	}
}
