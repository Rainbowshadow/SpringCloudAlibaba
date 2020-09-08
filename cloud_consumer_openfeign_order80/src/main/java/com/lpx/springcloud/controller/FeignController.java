package com.lpx.springcloud.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lpx.springcloud.entities.CommonResult;
import com.lpx.springcloud.entities.Vip;
import com.lpx.springcloud.service.FeignService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FeignController {

	@Resource
	private FeignService feignService;
	
	@GetMapping(value = "/list")
	public CommonResult<List<Vip>> select(){
			return feignService.select();
	}

	
	@GetMapping("/timeout")
	public  String timeout() {
		return feignService.timeout();
	}
	
}
