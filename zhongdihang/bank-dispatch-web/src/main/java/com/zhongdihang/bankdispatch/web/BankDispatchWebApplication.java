package com.zhongdihang.bankdispatch.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication(exclude={SessionAutoConfiguration.class})
public class BankDispatchWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDispatchWebApplication.class, args);
	}

	@GetMapping(path={"/",""})
	public String index(){
		return "forward:login.html";
	}
}
