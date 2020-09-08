package com.lpx.springcloud.Swagger;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public 	Docket	docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.rs.controller"))
				.build();
	}
	
	private ApiInfo	apiInfo() {
		Contact contact = new Contact("zq", "", "");
		return  new ApiInfo( 
				  "融盛",
			      "接口测试",
			      "1.0",
			      "com.rs",
			      contact ,
			      "Apche 2.0",
			      "",
			      new ArrayList<VendorExtension>());
	}
	
}
