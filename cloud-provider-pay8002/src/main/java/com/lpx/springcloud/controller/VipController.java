package com.lpx.springcloud.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lpx.springcloud.entities.CommonResult;
import com.lpx.springcloud.entities.Vip;
import com.lpx.springcloud.service.VipService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VipController {

	@Resource
	private VipService vipService;
	
	@Value("${server.port}")
	private String port;
	
//	@RequestMapping(value = "/list",method = RequestMethod.POST)
	@GetMapping(value = "/list")
	public CommonResult<List<Vip>> select(){
		List<Vip> l = vipService.slecteVip();
		log.info("****插入结果"+l);
		if(!l.isEmpty()) {
			return new CommonResult<List<Vip>>(200,"查找成功"+port,l);
		}else {
			return new CommonResult(500,"数据库数据为空");
		}
	}

	@GetMapping(value = "/ByOne/{id}")
	public CommonResult<Integer> ByOne(@PathVariable("id") Integer id){
		log.info("****插入结果"+id);
			return new CommonResult<Integer>(200,"查找成功",id);
	}
	
	@PostMapping(value = "/addVip")
	public CommonResult<String> addVip(@RequestBody Vip vip){
		int a  = vipService.addVip(vip);
		log.info("****插入结果"+a);
		if(a > 0) {
			return new CommonResult<String>(200,"添加成功",vip.getContent());
		}else {
			return new CommonResult(500,"添加失败");
		}
	}
	
	@GetMapping("/timeout")
	public  String timeout() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			return e.getMessage();
		}
		return port;
	}
	
	
}
