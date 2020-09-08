package com.lpx.springcloud;

import javax.servlet.MultipartConfigElement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;







@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Run{
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Run.class, args);
    }
}
