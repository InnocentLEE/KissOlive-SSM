package org.xgun.kissolive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/register")
	String register(){
		return "page/user/user_register.html";
	}

    @RequestMapping("/publish")
    String published(){
        return "page/admin/activitypublished.html";
    }

	@RequestMapping("/search")
	String search(){
		return "page/user/user_search.html";
	}

	@RequestMapping("/myshoppingcart")
	String myshoppingcart(){
		return "page/user/user_myshoppingcart.html";
	}

	@RequestMapping("/admin")
	String admin(){
		return "page/admin/frameset.html";
	}

	@RequestMapping("/pay")
	String pay(){
		return "page/user/user_pay.html";
	}

    @RequestMapping("/info")
    String info(){
        return "page/user/userinfo.html";
    }

	@RequestMapping("/production_detail")
	String productionDetail(){
		return "page/user/user_productdetail.html";
	}

	@RequestMapping("/order_detail")
	String orderDetail(){
		return "page/user/user_detailorder.html";
	}

	@RequestMapping("/pay_success")
	String paySuccess(){
		return "page/user/user_successed.html";
	}

	@RequestMapping("/order_list")
	String orderList(){
		return "page/user/user_orderlist.html";
	}

}
