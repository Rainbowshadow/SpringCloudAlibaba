package com.lpx.springcloud.controller;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lpx.springcloud.entities.CommonResult;
import com.lpx.springcloud.entities.Vip;
import com.lpx.springcloud.lb.LoadBalancer;
import com.lpx.springcloud.lb.MyLB;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	
	public static final String Urk = "http://localhost:8001";
//	public static final String Urk = "http://CLOUD-PAY-SERVICE";
//	cloud-pay-service
	
	@Resource
	private RestTemplate restTemplate;
	
	@Resource
	private LoadBalancer MyLB;
	
	@Resource
	private DiscoveryClient discoveryclient;
	
	
	
//	value 随便取
	@GetMapping("/cous/list")
	public CommonResult<List<Vip>> select(){
//		选择get还是post请求  （请求地址，参数，返回值类型）
		return restTemplate.getForObject(Urk+"/list", CommonResult.class);
	}
	
	@GetMapping("/cous/list2")
	public CommonResult<List<Vip>> select2(){
//		选择get还是post请求  （请求地址，参数，返回值类型）
		 ResponseEntity<CommonResult> enety = restTemplate.getForEntity(Urk+"/list", CommonResult.class);
		 if(enety.getStatusCode().is2xxSuccessful()) {
			 return enety.getBody();
		 }else {
			 return new CommonResult<List<Vip>>(500, "查询失败");
		 }
		 
	}
	
	@GetMapping(value = "/ByOne/{id}")
	public CommonResult<Integer> ByOne(@PathVariable("id") Integer id){
			return restTemplate.getForObject(Urk+"/ByOne/"+id, CommonResult.class);
	}
	
	@GetMapping(value = "/addVip")
	public CommonResult<String> addVip(Vip vip){
		return restTemplate.postForObject(Urk+"/addVip", vip,  CommonResult.class);
//		return restTemplate.postForEntity(Urk+"/addVip", vip,  CommonResult.class).getBody();
	}
	
	/**
	 * 测试自己的轮询算法
	 * @return
	 */
	@GetMapping("/cous/list3")
	public CommonResult<List<Vip>> select3(){
		List<ServiceInstance> ss = discoveryclient.getInstances("CLOUD-PAY-SERVICE");
		if(ss == null || ss.size() <= 0) {
			System.out.println("没有找到服务端");
			return null;
		}
		ServiceInstance sss =  MyLB.instances(ss);
		URI  url = sss.getUri();
//		选择get还是post请求  （请求地址，参数，返回值类型）
		 ResponseEntity<CommonResult> enety = restTemplate.getForEntity(url+"/list", CommonResult.class);
		 if(enety.getStatusCode().is2xxSuccessful()) {
			 return enety.getBody();
		 }else {
			 return new CommonResult<List<Vip>>(500, "查询失败");
		 }
		 
	}
}
 