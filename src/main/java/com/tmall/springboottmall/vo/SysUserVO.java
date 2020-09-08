package com.tmall.springboottmall.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(value = "返回页面的用户信息")
public class SysUserVO {

	  @ApiModelProperty(name="userName",value="用户名")
	  private String userName;
	  private String telphone;
	  private Integer age;
	  private String gender;
	
}
