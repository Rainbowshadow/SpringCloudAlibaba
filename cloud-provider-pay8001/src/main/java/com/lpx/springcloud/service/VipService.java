package com.lpx.springcloud.service;

import java.util.List;

import com.lpx.springcloud.entities.Vip;

public interface VipService {
	
	List<Vip>  slecteVip();
	
	
	int addVip(Vip vip);

}
