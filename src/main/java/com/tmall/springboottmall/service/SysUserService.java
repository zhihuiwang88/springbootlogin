package com.tmall.springboottmall.service;

import java.util.List;

import com.tmall.springboottmall.dto.SysUserDTO;
import com.tmall.springboottmall.exception.BusinessException;
import com.tmall.springboottmall.pojo.SysUser;

public interface SysUserService {

	List<SysUser> findData();

	int insertData(SysUserDTO sysUserDTO);


	SysUser denglu(String telphone, String password) throws BusinessException;

}
