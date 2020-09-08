package com.tmall.springboottmall.enums;

import com.tmall.springboottmall.error.CommonError;

/**
 * 枚举类
 * @author Dell
 *
 */
public enum EnumBusinessError implements CommonError{
	USER_NOT_EXIST("10001","用户不存在"),
	INVALID_PARAMETER("00001","参数不合法"),
	UNKNOWN_ERROR("20002","未知错误"),
	NOT_EXIST_ERROR("20003","不存在"),
	PASSWORD_ERROR("20004","密码错误"),
	
	
	;

	EnumBusinessError(String emCode,String emMsg) {
		this.emCode = emCode;
		this.emMsg = emMsg;
	}
	
	private String emCode;
	private String emMsg;
	
	@Override
	public String getErrorStatus() {
		
		return this.emCode;
	}

	@Override
	public String getErrMsg() {
		return this.emMsg;
	}

	@Override
	public CommonError setErrMsg(String msgErr) {
		this.emMsg = msgErr;
		return this;
	}

}
