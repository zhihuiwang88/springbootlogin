package com.tmall.springboottmall.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmall.springboottmall.controller.LoginController;
import com.tmall.springboottmall.dao.SysUserMapper;
import com.tmall.springboottmall.dto.SysUserDTO;
import com.tmall.springboottmall.enums.EnumBusinessError;
import com.tmall.springboottmall.exception.BusinessException;
import com.tmall.springboottmall.pojo.SysUser;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper  sysUserMapper;
	
	@Override
	public List<SysUser> findData() {
		return sysUserMapper.findData();
	}

	@Override
	public int insertData(SysUserDTO sysUserDTO) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDTO, sysUser);
		int i = 0;
		try {
			return	i = sysUserMapper.insertSelective(sysUser);
		} catch (Exception e) {
			e.getMessage();
		}
		 return i;
	}

	@Override
	public SysUser denglu(String telphone, String password) throws BusinessException {
		// 判断密码和手机
		if(StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
				throw new BusinessException(EnumBusinessError.NOT_EXIST_ERROR);
		}
		// 根据手机查询用户信息
		SysUser sysUser = sysUserMapper.selectByTelphone(telphone);
		if(sysUser == null) {
				throw new BusinessException(EnumBusinessError.NOT_EXIST_ERROR);
		}
		// 判断密码与数据库一样不
		if(!StringUtils.equals(sysUser.getPassword(), LoginController.mimaJiaMi(password))) {
			throw new BusinessException(EnumBusinessError.PASSWORD_ERROR);
		}
		return sysUserMapper.selectByPrimaryKey(sysUser.getId());
	}

}
