package org.xgun.kissolive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@Controller
@MapperScan({"org.xgun.kissolive.dao"})
public class Zqu004Application {


	public static void main(String[] args) {

		SpringApplication.run(Zqu004Application.class, args);
	}

	@RequestMapping("/")
	String index(){
		return "pages/OnlineConsult.html";
	}

	@RequestMapping("/home")
	String home(){
		return "page/user/user_home.html";
	}
}
