package com.lpx.springcloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lpx.springcloud.entities.CommonResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FallBackController {

	@RequestMapping(value = "/fallbackcontroller", method = RequestMethod.GET)
	@ResponseBody
    public CommonResult<String> fallbackcontroller(){
		
		return new CommonResult<String>(501, "由于服务器内部出现不明飞行物，导致访问受到干扰，请紧急联系java攻城狮！！！");
    }
	
}
