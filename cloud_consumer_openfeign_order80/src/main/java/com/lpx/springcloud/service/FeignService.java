package com.lpx.springcloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.lpx.springcloud.entities.CommonResult;
import com.lpx.springcloud.entities.Vip;

@Component //表示扫描这个类
@FeignClient(value="CLOUD-PAY-SERVICE")
public interface FeignService {
	
	@GetMapping(value = "/list")
	public CommonResult<List<Vip>> select();
	

	@GetMapping("/timeout")
	public  String timeout();
	
}
