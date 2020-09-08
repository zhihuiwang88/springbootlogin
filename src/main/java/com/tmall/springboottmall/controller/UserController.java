package com.tmall.springboottmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.springboottmall.pojo.SysUser;
import com.tmall.springboottmall.service.SysUserService;
import com.tmall.springboottmall.util.CommonReturnType;

@RequestMapping("/sysUser")
@RestController
public class UserController {

	
	@Autowired
	private SysUserService  sysUserService;
	
	
	
	
}
