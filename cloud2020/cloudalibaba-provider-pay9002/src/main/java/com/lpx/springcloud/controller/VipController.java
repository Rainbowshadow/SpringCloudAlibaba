package com.lpx.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.lpx.springcloud.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VipController {

	
	@Value("${server.port}")
	private String port;
	
	
	@GetMapping(value = "/ByOne/{id}")
	public CommonResult<Object> ByOne(@PathVariable("id") Integer id){
		log.info("****插入结果"+id);
			return new CommonResult<Object>(200,"查找成功",port+"======"+id);
	}
	
}
