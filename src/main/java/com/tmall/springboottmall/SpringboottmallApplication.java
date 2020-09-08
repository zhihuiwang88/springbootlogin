package com.tmall.springboottmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tmall.springboottmall.dao")
public class SpringboottmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringboottmallApplication.class, args);
	}

}
