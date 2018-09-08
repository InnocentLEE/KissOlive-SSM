package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.service.IInnocentService;
import org.xgun.kissolive.utils.FTPSSMLoad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Lee on 2018/9/7.
 */
@Controller
public class InnocentController {

    @Autowired
    private IInnocentService iInnocentService;


    @RequestMapping(value="/production/add_brand.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addBrand(HttpSession session, HttpServletRequest request,
                                   @RequestParam("logo_img") MultipartFile brandLogo,
                                   @RequestParam("brand_name") String brandName){
        Map map = FTPSSMLoad.upload(brandLogo,request,"/video/");
        System.out.println(map.get("http_url"));
        System.out.println(brandName);
        return ServerResponse.createBySuccess(map);
    }
}
