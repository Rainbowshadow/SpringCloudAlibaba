package com.lpx.ribbon;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
//将 RestTemplate  注册到容器
@Configuration
public class MyRibbonRule {
	
	@Bean
	public IRule MyRule() {
		return new RandomRule();//ribbon随机
	}

}
