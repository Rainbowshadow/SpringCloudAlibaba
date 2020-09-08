package com.lpx.springcloud.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;
import com.lpx.springcloud.entities.CommonResult;
import com.lpx.springcloud.token.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	
	
//	@Autowired
//    private LoginService loginService;
    /** 
     * 用户登录同时返回jwt校验码
     * @param username
     * @param secret
     * @return
     */
	@RequestMapping(value = "/loginYanzheng1", method = RequestMethod.GET)
	@ResponseBody
    public Map<String,Object> loginYanzheng(){//@RequestBody com.lpx.springcloud.mo.User user
	 	
		
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String username=user.getUsername();
//		String secret=user.getPassword();
		String username="asd";
		String secret="asd";
    	Map<String,Object> map=new HashMap<String, Object>();
//    	List<User> list=loginService.loginYanzheng(user);
//        if (list!=null && !list.isEmpty()){
            String jwt =JwtUtil.sign(username,secret);                    
            JSONObject userJ = new JSONObject();
            userJ.put("username",username);
            userJ.put("secret",secret);           
            redisTemplate.opsForValue().set(jwt,userJ.toString(),10*60*60, TimeUnit.SECONDS);  		
//    		map.put("data", list.get(0));
    		map.put("token", jwt);
    		map.put("msg", "请求成功");
    		map.put("err", "0");
            return map;
//        } else {
//        	map.put("msg", "用户名或密码错误");
//        	map.put("err", "-1");
//            return map;
//        }
    }

	 /** 
     * 限制每个用户调用一次   或者  相隔多长时间允许调用一次
     * @param username
     * @param secret
     * @return
	 * @throws InterruptedException 
     */
	@RequestMapping(value = "/resis/{id}", method = RequestMethod.GET)
	@ResponseBody
    public String resis(@PathVariable("id") String user) throws InterruptedException{//@RequestBody com.lpx.springcloud.mo.User user
//		只允许一次
//		synchronized(this){
//			String absentValue = redisTemplate.opsForValue().get(user); 
//			if(absentValue == null || absentValue.equals("") ){  
//			    redisTemplate.opsForValue().setIfAbsent(user,"false");  
//			}else {
//				 if(absentValue.equals("false")) {
//					 System.out.println("次数已达上限");
//					 return "次数已达上限";  	
//					}
//			}       
//	   } 
		
//		设置时间的
		synchronized(this){
			String absentValue = redisTemplate.opsForValue().get(user); 
			if(absentValue == null || absentValue.equals("") ){  
			    redisTemplate.opsForValue().setIfAbsent(user,"false",600,TimeUnit.SECONDS);  
			}else {
				 if(absentValue.equals("false")) {
					 System.out.println("次数已达上限");
					 return "次数已达上限";  	
					}
			}       
	   } 
		
	            Thread.sleep(5*1000);  
	            System.out.println("写入数据库");
				return "写入数据库";  		
	    }

	
    /**
     * 校验token是否失败
     * @param token
     * @param username
     * @param secret
     * @return
     */
    @RequestMapping(value = "verify")
    public Map<String,String> verify(String token, String username, String secret){
    	Map<String,String> map=new HashMap<String, String>();
        if (JwtUtil.verify(token,username,secret)){
        	map.put("success", "校验成功!");
            return map;
        } else {
        	map.put("error", "校验失败");
            return map;
        }
    }



}
