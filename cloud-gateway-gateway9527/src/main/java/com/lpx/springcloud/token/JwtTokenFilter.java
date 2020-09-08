package com.lpx.springcloud.token;


import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.PathContainer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 参数参考 https://blog.csdn.net/tianyaleixiaowu/article/details/83375246
 * response参考 https://bbs.csdn.net/topics/392412604?list=11074255
 */
@Component
public class JwtTokenFilter implements GlobalFilter,Ordered {

	@Autowired
    private StringRedisTemplate redisTemplate;
	
	 private String[] skipAuthUrls;

	    private ObjectMapper objectMapper;

	    public JwtTokenFilter(ObjectMapper objectMapper) {
	        this.objectMapper = objectMapper;
	    }

	
	
	  /**
     * 加载过滤器的顺序，数字越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
    
    /**
     * 过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();

        //跳过不需要验证的路径
        String local_url = "/loginYanzheng1";
        //本地静态资源  不需要验证
        String local_url2 = "/gateway/";
        //如果是登录接口不进行token验证
        if (url.contains(local_url)){
            return chain.filter(exchange);
        }else if(url.contains(local_url2)){
        	 return chain.filter(exchange);
        }
        
        //获取token
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        ServerHttpResponse resp = exchange.getResponse();
        resp.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        if(null == token){
			 return getVoidMono(resp,ResponseCodeEnum.TOKEN_MISSION);
        }
        if (token.equals("")){
            return getVoidMono(resp,ResponseCodeEnum.TOKEN_MISSION);
        }
        String account=redisTemplate.opsForValue().get(token);
        JSONObject jsonObject = new JSONObject();
        
        // 如果缓冲中有该账号，则返回value
        if (account!=null&&!"".equals(account)&&account.length()>2) {
            jsonObject.put("state",1);
            jsonObject.put("account",account);
        } else {
            jsonObject.put("state",0);
        }

        if (jsonObject.getInteger("state") == 0){
            return getVoidMono(resp,ResponseCodeEnum.TOKEN_EXPIRED);
        }
        
        JSONObject accout = jsonObject.getJSONObject("account");
        String username = accout.getString("username");
        String secret = accout.getString("secret");
        if(!JwtUtil.verify(token,username,secret)){
            return getVoidMono(resp,ResponseCodeEnum.TOKEN_INVALID);
        }
        return chain.filter(exchange);
    }

		private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ResponseCodeEnum responseCodeEnum) {
	         serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
	         ResponseResult responseResult = ResponseResult.error(responseCodeEnum.getCode(), responseCodeEnum.getMessage());
	         DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(responseResult).getBytes());
	         return serverHttpResponse.writeWith(Flux.just(dataBuffer));
	     }
	
}