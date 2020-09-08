package com.lpx.springcloud.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult<T> {

	
	private Integer code;
	
	private String message;
	
	private T  data;
	
	public CommonResult(Integer code,String message) {
		
		this(code,message,null);
	}

	public CommonResult(Integer code2, String message2, T object) {
		this.code = code2;
		this.message = message2;
		this.data = object;
	}
}
