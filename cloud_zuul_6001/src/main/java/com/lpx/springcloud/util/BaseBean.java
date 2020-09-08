package com.lpx.springcloud.util;


import java.util.Map;


public  class BaseBean {
    private String sign;


    public Map<String, Object> toMap( Map<String, Object> map ) {
    	
        map.put("sign", Md5Encrypt.createSign(map));
        return map;
    }
}
