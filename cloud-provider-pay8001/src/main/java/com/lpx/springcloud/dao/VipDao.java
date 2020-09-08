package com.lpx.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lpx.springcloud.entities.Vip;

@Mapper
public interface VipDao {
	
	List<Vip>  slecteVip();
	
	int addVip(Vip vip);

}
