package org.xgun.kissolive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.InnocentMapper;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.service.IInnocentService;

/**
 * Created by Lee on 2018/9/7.
 */
@Service("iInnocentService")
public class InnocentServiceImpl implements IInnocentService {

    @Autowired
    private InnocentMapper innocentMapper;

    @Override
    public ServerResponse addBrand(Brand brand){
        boolean result = innocentMapper.insertBrand(brand)>0;
        if(result)
            return ServerResponse.createBySuccessMessage("添加品牌成功");
        else
            return ServerResponse.createByErrorMessage("添加品牌失败");
    }
}
