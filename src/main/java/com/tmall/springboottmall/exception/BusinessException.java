package com.tmall.springboottmall.exception;

import com.tmall.springboottmall.error.CommonError;

/**
 * 管理异常
 * 
 * @author Dell
 *
 */

public class BusinessException  extends Exception implements CommonError{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2958640140041680724L;
	
	@SuppressWarnings("unused")
	private CommonError commonError;
	
	public BusinessException(CommonError commonError) {
		super();
		this.commonError = commonError;
	}
	
	public BusinessException(CommonError commonError,String errMsg) { 
		super();
		this.commonError = commonError;
		this.commonError.setErrMsg(errMsg);
	}
	

	@Override
	public String getErrorStatus() {
		return commonError.getErrorStatus();
	}

	@Override
	public String getErrMsg() {
		return commonError.getErrMsg();
	}

	@Override
	public CommonError setErrMsg(String msgErr) {
		this.commonError.setErrMsg(msgErr);
		return this;
	}

}
