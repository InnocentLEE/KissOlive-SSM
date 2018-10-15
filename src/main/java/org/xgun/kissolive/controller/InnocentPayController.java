package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.service.IInnocentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Lee on 2018/10/15.
 */
@Controller
public class InnocentPayController {

    @Autowired
    private IInnocentService iInnocentService;

    @RequestMapping(value = "/pay.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse pay(HttpSession session, @RequestParam("order_id") int id, HttpServletRequest request){
        return null;
    }
}
