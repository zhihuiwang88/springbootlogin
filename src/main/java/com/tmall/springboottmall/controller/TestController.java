package com.tmall.springboottmall.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmall.springboottmall.dao.SysUserMapper;
import com.tmall.springboottmall.enums.EnumBusinessError;
import com.tmall.springboottmall.exception.BaseController;
import com.tmall.springboottmall.exception.BusinessException;
import com.tmall.springboottmall.pojo.SysUser;
import com.tmall.springboottmall.service.SysUserService;
import com.tmall.springboottmall.util.CommonReturnType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;



@Api(value="用户控制器")
@RequestMapping("/usertest")
@RestController
public class TestController extends BaseController{

	@Autowired
	private SysUserMapper  sysUserMapper;
	
	@Autowired
	private  SysUserService sysUserService;
	
	/**
	 * 测试类
	 * http://localhost:8083/user/login
	 * @return
	 */
	@ApiOperation(value="登录页面")
	@PostMapping("/logintest")
	public String testString() {
		return "hello word"; 
	}
	
	@ApiOperation(value="根据ID查询用户信息")
	@ApiImplicitParam(name="id",value="用户ID")
	@GetMapping("/byId")
	public CommonReturnType byId(@RequestParam Integer id) throws BusinessException {
		SysUser sys =sysUserMapper.selectByPrimaryKey(id);
		if(sys == null) {
			throw new BusinessException(EnumBusinessError.USER_NOT_EXIST);
		}
		return CommonReturnType.create(sys);
	}
	
	
	
//	@ApiOperation(value="查询所有的用户信息")
	@GetMapping("/findAll")
	public List<SysUser> findData() {
		return  sysUserService.findData();
	}
	
	
	
	
	
	/**
	 * 第二次运行main方法，SysUserMapper.xml中的CRUD会再次插入。所以把XXXmapper.xml删除重新生成
	 * mybatis-generator自动生成pojo、dao、mapper、
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		File configFile = new File("mybatis-generator.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myGenerator = new MyBatisGenerator(config,callback,warnings);
		myGenerator.generate(null);
		System.out.println("自动生成代码成功！");
	}
	
	
}
