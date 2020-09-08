package com.lpx.springcloud.util;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootConfiguration  
public class Mvc extends WebMvcConfigurerAdapter{
//	 public void addInterceptors(InterceptorRegistry registry) {  
//	        registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");  
//	    }  
	    
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	//addResourceHandler是指你想在url请求的路径
	        //addResourceLocations是图片存放的真实路径
	    	registry.addResourceHandler("/zhsl/**").addResourceLocations("file:C://zhsl/");
	        super.addResourceHandlers(registry);
	    }

}
