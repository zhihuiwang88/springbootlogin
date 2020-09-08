package com.tmall.springboottmall.util;

import lombok.Data;



/**
 * 返回结果统一封装
 * @author Dell
 *
 */

@Data
public class CommonReturnType {

	private String status;
	private Object data;
	
	public static CommonReturnType create(Object result) {
		return CommonReturnType.creates(result,"succeed");
	}

	public static CommonReturnType error(Object result) {
		return CommonReturnType.creates(result,"error");
	}
	
	public static CommonReturnType creates(Object data,String  status) {
		CommonReturnType commonReturnType = new CommonReturnType();
		commonReturnType.setStatus(status);
		commonReturnType.setData(data);
		return commonReturnType;
	}
	
	
}
