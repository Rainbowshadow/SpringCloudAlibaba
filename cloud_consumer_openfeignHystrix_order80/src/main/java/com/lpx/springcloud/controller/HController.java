package com.lpx.springcloud.controller;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lpx.springcloud.service.hystrix_pay;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@DefaultProperties(defaultFallback = "hystrix_Fallback")//注解配置全局 fallback
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
//	此方法 Hystrix 服务降级  处理   超时直接 友情提示   运行异常  或者 超时异常   都会触发
	@HystrixCommand(fallbackMethod = "hystrix_pay_TimeoutH",commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "4000")//表示这个线程的超时时间是3秒
	})
//	缺点 每个方法都设置  服务降级 fallback  代码膨胀     有没有全局处理方法？
	public  String hystrix_pay_Timeout(@PathVariable("id") Integer id) {
		String ru = hystrix_pay.hystrix_pay_Timeout(id);
		log.info(ru);
		return ru;
	}
//	缺点  与逻辑接口混在一块    混乱
	public  String hystrix_pay_TimeoutH(Integer id ) {
		return "线程池 - Hystrix80"+Thread.currentThread().getName()+" 系统繁忙请稍后再试" + id+"\t";
	}
	
	
	
	@GetMapping("/hystrix_pay_Timeout2/{id}")
//	此方法 Hystrix 服务降级  处理   超时直接 友情提示   运行异常  或者 超时异常   都会触发
	//使用全局的fallback  服务降级方法
	@HystrixCommand
	public  String hystrix_pay_Timeout2(@PathVariable("id") Integer id) {
		String ru = hystrix_pay.hystrix_pay_Timeout(id);
		log.info(ru);
		return ru;
	}
//	这个是全局fallback服务降级 方法
	public  String hystrix_Fallback() {
		return "系统繁忙请稍后再试";
	}
	
}
