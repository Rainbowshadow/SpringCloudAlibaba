package com.lpx.springcloud.service;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;




@Service
public class hystrix_pay {

	
	public  String hystrix_pay_ok(Integer id ) {
		return "线程池"+Thread.currentThread().getName()+"  hystrix_pay_ok" + id+"\t"+"123";
	}
	
//	此方法 Hystrix 服务降级  处理   超时直接 友情提示   运行异常  或者 超时异常   都会触发
	@HystrixCommand(fallbackMethod = "hystrix_pay_TimeoutH",commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")//表示这个线程的超时时间是3秒
	})
	public  String hystrix_pay_Timeout(Integer id ) {
		int time = 3;
//		int age = 10/0; 
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (Exception e) {
		}
		return "线程池"+Thread.currentThread().getName()+"  hystrix_pay_Timeout" + id+"\t"+"时长："+time;
	}
	
	public  String hystrix_pay_TimeoutH(Integer id ) {
		return "线程池 - Hystrix"+Thread.currentThread().getName()+" 系统繁忙请稍后再试" + id+"\t";
	}
	//==============================================================================================
//	 ===========================   hystrix  break
//	此方法 Hystrix 服务降级  处理   超时直接 友情提示   运行异常  或者 超时异常   都会触发
	@HystrixCommand(fallbackMethod = "hystrix_pay_Break",commandProperties = {
			@HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
			@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间范围
			@HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少跳闸
	})
	public  String hystrix_pay_BreakOut(Integer id ) {
		if(id < 0) {
			throw new RuntimeException("id ***********  不能为负数");
		}
		return "线程池"+Thread.currentThread().getName()+"  hystrix_pay_BreakOut" + id+"\t"+"********："+id;
	}
	
	public  String hystrix_pay_Break(Integer id ) {
		return "线程池 - HystrixBreaker  "+Thread.currentThread().getName()+"  hystrix_pay_Break" + id+"\t"+"********："+id;
	}
	
	

}
