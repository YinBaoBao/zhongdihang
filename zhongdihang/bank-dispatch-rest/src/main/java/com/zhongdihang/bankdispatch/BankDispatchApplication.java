package com.zhongdihang.bankdispatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class BankDispatchApplication {
	@Value("${upLoda.FileSize}")
	private Long fileSize;
	@Value("${upLoda.FileSizeAll}")
	private Long fileSizeAll;



	public static void main(String[] args) {
		SpringApplication.run(BankDispatchApplication.class, args);
	}

	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//====================================================
		//文件最大
		//====================================================
		factory.setMaxFileSize(fileSize+"MB"); //KB,MB
		//====================================================
		// 设置总上传数据总大小
		//====================================================
		factory.setMaxRequestSize(fileSizeAll+"MB");

		return factory.createMultipartConfig();
	}
}
