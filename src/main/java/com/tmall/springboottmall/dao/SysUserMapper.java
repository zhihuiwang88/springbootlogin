package com.tmall.springboottmall.dao;

import java.util.List;

import com.tmall.springboottmall.pojo.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);
    SysUser selectByTelphone(String telphone);
    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	List<SysUser> findData();
}