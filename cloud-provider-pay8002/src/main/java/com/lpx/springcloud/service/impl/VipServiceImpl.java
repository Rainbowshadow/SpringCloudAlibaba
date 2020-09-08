package com.lpx.springcloud.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lpx.springcloud.dao.VipDao;
import com.lpx.springcloud.entities.Vip;
import com.lpx.springcloud.service.VipService;

@Service
public class VipServiceImpl implements VipService {

//	@Resource  java  自带实现自动注入
	@Autowired
	private VipDao vipDao;
	
	@Override
	public List<Vip> slecteVip() {
		// TODO Auto-generated method stub
		return vipDao.slecteVip();
	}

	@Override
	public int addVip(Vip vip) {
		// TODO Auto-generated method stub
		return vipDao.addVip(vip);
	}

}
