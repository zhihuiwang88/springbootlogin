package com.tmall.springboottmall.pojo;

import lombok.Data;

@Data
public class SysUser {
    private Integer id;

    private String userName;

    private String password;

    private String salt;

    private String telphone;

    private Integer age;

    private String gender;

    private String registerMode;

    private String thirdPartyId;

   
}