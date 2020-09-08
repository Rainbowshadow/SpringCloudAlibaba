package com.lpx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.lpx.ribbon.MyRibbonRule;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAY-SERVICE",configuration = MyRibbonRule.class)//引入自定义ribbon轮训规则
public class Run {
	
	public static void main(String[] args) {
		SpringApplication.run(Run.class,args);
	}
}
