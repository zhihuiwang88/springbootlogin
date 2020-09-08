package com.tmall.springboottmall.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tmall.springboottmall.enums.EnumBusinessError;
import com.tmall.springboottmall.util.CommonReturnType;

/**
 * 统一异常处理
 * @author Dell
 *
 */
public class BaseController  {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object handlerException(HttpServletRequest request,Exception exception) {
		Map<String ,Object> map = new HashMap<>();
		if(exception instanceof BusinessException) {
			BusinessException businessException = (BusinessException) exception;
			map.put("emCode", businessException.getErrorStatus());
			map.put("emMsg", businessException.getErrMsg());
			return CommonReturnType.creates(map, "fail");
		}else {
			map.put("emCode", EnumBusinessError.UNKNOWN_ERROR.getErrorStatus());
			map.put("emMsg", EnumBusinessError.UNKNOWN_ERROR.getErrMsg());
		}
		return CommonReturnType.creates(map, "fail");
	}
	
}
