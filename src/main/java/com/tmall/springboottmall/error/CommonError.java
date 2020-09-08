package com.tmall.springboottmall.error;

public interface CommonError {

	public String getErrorStatus();
	public String getErrMsg();
	
	CommonError setErrMsg(String msgErr);
	
}
