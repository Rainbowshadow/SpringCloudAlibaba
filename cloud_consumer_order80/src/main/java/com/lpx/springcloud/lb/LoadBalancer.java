package com.lpx.springcloud.lb;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;

//自定义 ribbon 负载接口类
public interface LoadBalancer {

	
	ServiceInstance instances(List<ServiceInstance> serviceInstance);
}
