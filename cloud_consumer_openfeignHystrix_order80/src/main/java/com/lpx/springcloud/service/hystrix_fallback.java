package com.lpx.springcloud.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class hystrix_fallback implements hystrix_pay {

	@Override
	public String hystrix_pay_ok(Integer id) {
		return "fallback----------------hystrix_pay";
	}

	@Override
	public String hystrix_pay_Timeout(Integer id) {
		return "fallback----------------hystrix_pay";
	}

}
