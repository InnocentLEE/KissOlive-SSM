package org.xgun.kissolive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xgun.kissolive.dao.DemoMapper;
import org.xgun.kissolive.pojo.Demo;
import org.xgun.kissolive.service.IDemoService;

import java.util.List;

/**
 * Created by Lee on 2018/8/31.
 */
@Service("iDemoService")
public class DemoServiceImpl implements IDemoService{
    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List demo() {
        List<Demo> demoList =  demoMapper.findAll();
        return demoList;
    }
}
