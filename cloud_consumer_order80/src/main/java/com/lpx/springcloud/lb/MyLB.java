package com.lpx.springcloud.lb;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

@Component
public class MyLB  implements LoadBalancer {
	
	//设置初始默认值
	private AtomicInteger  AtomicInteger = new AtomicInteger(0);
	
	private final int getAndIncrement() {
		int current;
		int next;
		
		do {
			current = this.AtomicInteger.get();
			next = current >= Integer.MAX_VALUE ? 0 : current+1;
		}
		while(!this.AtomicInteger.compareAndSet(current, next));
	return next;
		
	}

	@Override
	public ServiceInstance instances(List<ServiceInstance> serviceInstance) {
		int index =  getAndIncrement() % serviceInstance.size();
		return serviceInstance.get(index);
	}

}
