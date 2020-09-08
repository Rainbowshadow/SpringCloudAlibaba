package com.lpx.springcloud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DateFormat {
	// 时间格式转换
	public Map<String, Object> dateFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		
		
		//现在默认当前系统时间
		String nows =  sdf.format(new Date());
		
		
		//订单号
		String random  = sdf1.format(new Date())+(int)((Math.random()*900)+100);
		
		String currs = sdf1.format(new Date());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("now", nows);
		map.put("random", random);
		map.put("curr", currs);
		
		return map;


	}
	
	
}
