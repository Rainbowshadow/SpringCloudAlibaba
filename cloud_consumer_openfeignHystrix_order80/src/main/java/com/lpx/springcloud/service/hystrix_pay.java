package com.lpx.springcloud.service;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;



@Component
@FeignClient(value = "cloud-hystrix-service",fallback = hystrix_fallback.class)
public interface hystrix_pay {

	@GetMapping("/hystrix_pay_ok/{id}")
	public  String hystrix_pay_ok(@PathVariable("id") Integer id);
	
	@GetMapping("/hystrix_pay_Timeout/{id}")
	public  String hystrix_pay_Timeout(@PathVariable("id") Integer id);
	
}
