package org.xgun.kissolive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xgun.kissolive.service.IDemoService;

import java.util.List;

/**
 * Created by Lee on 2018/8/31.
 */
@RestController
public class DemoController {

    @Autowired
    private IDemoService iDemoService;

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public List demo(){
        return iDemoService.demo();
    }

}
