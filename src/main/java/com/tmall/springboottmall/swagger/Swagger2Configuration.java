package com.tmall.springboottmall.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




/**
 * http://localhost:8083/swagger-ui.html
 * 
 * @author Dell
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

	//api接口包扫描路径
	public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.tmall.springboottmall";
	public static final String VERSION = "1.0.0";
	
	   @Bean
	   public Docket createRestApi() {
	       return new Docket(DocumentationType.SWAGGER_2)
	                   .apiInfo(apiInfo())
	                   .select()
	.apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE)) 
	                   .paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
	                   .build();
	   }
	 
	   /**
	    * 用来创建该 Api 的基本信息（这些基本信息会展现在文档页面中）
	    * @return
	    */
	private ApiInfo apiInfo() {
		// ApiSelectorBuilder实例用来控制哪些接口暴露给 Swagger 来展现，本例采用指定扫描的包路径来定义
	       return new ApiInfoBuilder()
	                   .title("单词计数服务") //设置文档的标题
	                   .description("单词计数服务 API 接口文档") // 设置文档的描述
	                   .version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
//	                   .termsOfServiceUrl("http://yapi.qcgoshopping.com") // 设置文档的License信息->1.3 License information
//	                   .contact(new Contact("zgdqh", "http://yapi.qcgoshopping.com", "208132179@qq.com"))
	                   .build();
	   }
	   
	   
}
