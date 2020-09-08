package com.tmall.springboottmall.controller;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tmall.springboottmall.dto.SysUserDTO;
import com.tmall.springboottmall.enums.EnumBusinessError;
import com.tmall.springboottmall.exception.BusinessException;
import com.tmall.springboottmall.pojo.SysUser;
import com.tmall.springboottmall.service.SysUserService;
import com.tmall.springboottmall.util.CommonReturnType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



/**
 * 登录控制器
 * @author Dell
 *跨域：@CrossOrigin
 */
@Api("登录控制器")
@RequestMapping("/user")
@Controller
//@CrossOrigin(allowedHeaders="*",allowCredentials="true",origins="http://localhost:8083")
//@CrossOrigin(origins="*")
public class LoginController {

	@Autowired
	private SysUserService  sysUserService;
	
	@SuppressWarnings("unused")
	@Autowired
	private  HttpServletRequest httpServletRequest;
	
	
	
  /**
   * localhost:8083/user/register
   * @param userName 用户名
   * @param password 密码
   * @param age 年龄
   * @param telphone 手机号
   * @param gender 性别
   * @param code 验证码
   * @return
   */
	@ApiOperation(value="注册页面")
	@ApiImplicitParams(value = { @ApiImplicitParam(value="手机号",name="telphone"),
			@ApiImplicitParam(value="用戶名",name="userName"),
			@ApiImplicitParam(value="密码",name="password"),
			@ApiImplicitParam(value="年龄",name="age"),
			@ApiImplicitParam(value="性别",name="gender"),
			@ApiImplicitParam(value="验证码",name="code")
	})
	@RequestMapping(value="/register",method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	public CommonReturnType registerMsg(@RequestParam("userName") String userName,@RequestParam("password") String password,
			@RequestParam("age") Integer age,@RequestParam("telphone") String telphone,
			@RequestParam("gender") String gender,@RequestParam("code") String code) {
		// 点击获取验证码，手机号不存在直接跳转注册页面
		System.out.println("code:" + code);
		System.out.println("userName:" + userName);
		System.out.println("password:" + password);
		System.out.println("age:" + age);
		System.out.println("gender:" + gender);
		// 判断获取验证码正确否
		String inSesssionOtpCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);
		System.out.println("inSesssionOtpCode:" + inSesssionOtpCode);
		
		if(!StringUtils.equals(code, inSesssionOtpCode)) {
			 try {
				throw new BusinessException(EnumBusinessError.UNKNOWN_ERROR);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		// 进行注册
		SysUserDTO sysUserDTO = new SysUserDTO();
		sysUserDTO.setAge(age);
		sysUserDTO.setGender(gender);
		
		String mimaPassword = LoginController.mimaJiaMi(password);
		sysUserDTO.setPassword(mimaPassword);
		sysUserDTO.setTelphone(telphone);
		sysUserDTO.setUserName(userName);
			int result = sysUserService.insertData(sysUserDTO);
		
		return CommonReturnType.create(null);
	}
	
	/**
	 * 密码加密
	 */
	public static String mimaJiaMi(String password) {
		long random = System.currentTimeMillis();
		 ByteArrayOutputStream bao = new ByteArrayOutputStream();
	     DataOutputStream dos =new DataOutputStream(bao);
	     try {
			dos.writeLong(random);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     byte[] byteSalt =bao.toByteArray();
	    // 新盐值
	     String newSalt = "mima" + random;
	     String salt = "mima";
		// 将密码转ByteSource对象
		ByteSource source = ByteSource.Util.bytes(password.getBytes());
		SimpleHash simpleHash = new SimpleHash("MD5", source,salt);
		String newMima = simpleHash.toHex();
		return  newMima;
	}
	
	
	
	
	
	/**
	 * localhost:8083/user/loginTwo
	 * 手机验证码为六位随机数:100000--999999之间
	 * @param telphone
	 * @return
	 */
	@SuppressWarnings("unused")
	@ApiOperation(value="手机验证码")
	@ApiImplicitParam(value="手机号",name="telphone")
	@RequestMapping(value="/loginTwo",method=RequestMethod.POST)
	@ResponseBody
	public CommonReturnType  login(@RequestParam("telphone") String  telphone) {
		int random = new Random().nextInt(899999) + 100000;
		String randomCode = String.valueOf(random);
		httpServletRequest.getSession().setAttribute(telphone, randomCode);
		// 通過短信发给用户
		System.out.println("telphone:" + telphone+",randomCode=" + randomCode);
		return CommonReturnType.create(null);
	}
	
	/**
	 * 用户登录
	 * @param sysUserDTO
	 * @return
	 */
	@ApiOperation(value="登录页面")
	@ApiImplicitParams(value={@ApiImplicitParam(value="手机号",name="telphone"),
			@ApiImplicitParam(value="密码",name="password")})
	@RequestMapping(value="/denglu",method=RequestMethod.POST)
	@ResponseBody
	public CommonReturnType denglu(@RequestParam("telphone") String telphone,@RequestParam("password") String password) {
		SysUser sysUser = null;
		try {
			sysUser = sysUserService.denglu(telphone,password);
			//登录凭证加入session中
			this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
			this.httpServletRequest.getSession().setAttribute("LOGIN_USER", sysUser);
		} catch (BusinessException businessException) {
			System.out.println("msg:" + businessException.getErrMsg());
			businessException.printStackTrace();
		}
			return CommonReturnType.create(sysUser);
	}
	
	/**
	 * 可以访问注册页面和登录页面和获取手机验证码页面
	 * @param path 
	 * @return
	 */
	@RequestMapping("/{path}")
	public String allData(@PathVariable String path) {
		return path;
	}
	
	
	
}
