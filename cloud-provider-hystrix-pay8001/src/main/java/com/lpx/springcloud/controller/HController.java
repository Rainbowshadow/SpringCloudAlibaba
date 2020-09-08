package com.lpx.springcloud.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lpx.springcloud.service.hystrix_pay;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HController {

	@Resource
	private hystrix_pay hystrix_pay;
	
	@Value("${server.port}")
	private String port;
	

	
	@GetMapping("/hystrix_pay_ok/{id}")
	public  String hystrix_pay_ok(@PathVariable("id") Integer id) {
		String ru = hystrix_pay.hystrix_pay_ok(id);
		log.info(ru);
		return ru;
	}
	
	
	@GetMapping("/hystrix_pay_Timeout/{id}")
	public  String hystrix_pay_Timeout(@PathVariable("id") Integer id) {
		String ru = hystrix_pay.hystrix_pay_Timeout(id);
		log.info(ru);
		return ru;
	}
//	服务熔断
	@GetMapping("/hystrix_pay_BreakOut/{id}")
	public  String hystrix_pay_BreakOut(@PathVariable("id") Integer id ) {
		String ru = hystrix_pay.hystrix_pay_BreakOut(id);
		log.info(ru);
		return ru;
	}
	
}
