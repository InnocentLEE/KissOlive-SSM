package org.xgun.kissolive.service.impl;

import ch.qos.logback.core.joran.event.SaxEventRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xgun.kissolive.common.Const;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.InnocentMapper;
import org.xgun.kissolive.pojo.Brand;
import org.xgun.kissolive.pojo.Function;
import org.xgun.kissolive.pojo.Hotspot;
import org.xgun.kissolive.service.IInnocentService;

import java.util.List;

/**
 * Created by Lee on 2018/9/7.
 */
@Service("iInnocentService")
public class InnocentServiceImpl implements IInnocentService {

    @Autowired
    private InnocentMapper innocentMapper;

    @Override
    public ServerResponse addBrand(Brand brand) {
        boolean result = innocentMapper.insertBrand(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("添加品牌成功");
        else
            return ServerResponse.createByErrorMessage("添加品牌失败");
    }

    @Override
    public ServerResponse getBrandList() {
        List<Brand> list = innocentMapper.selectBrand();
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse getBrandPutOn() {
        List<Brand> list = innocentMapper.selectBrandByStatus(Const.BRAND_PUT_ON_STATUS);
        return ServerResponse.createBySuccess("查询成功", list);
    }

    @Override
    public ServerResponse changeBrandPutOnStatus(Brand brand) {
        boolean result = innocentMapper.updateBrandStatusById(brand) > 0;
        if (result)
            return ServerResponse.createBySuccessMessage("修改成功");
        else
            return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public ServerResponse addHotspot(Hotspot hotspot) {
        boolean isExist = innocentMapper.countHotspotByDescribe(hotspot.getDescribe())>0;
        if(isExist)
            return ServerResponse.createByErrorMessage("添加选购热点失败，该选购热点已存在");
        innocentMapper.insertHotspot(hotspot);
        Integer id = hotspot.getId();
        if (id != null) {
            hotspot = innocentMapper.selectHotspotById(id);
            return ServerResponse.createBySuccess("添加选购热点成功", hotspot);
        } else {
            return ServerResponse.createByErrorMessage("添加选购热点失败");
        }
    }

    @Override
    public ServerResponse getHotspotList(){
        List<Hotspot> list = innocentMapper.selectHotspot();
        return ServerResponse.createBySuccess("获取选购热点成功",list);
    }

    @Override
    public ServerResponse addFunction(Function function) {
        boolean isExist = innocentMapper.countFunctionByDescribe(function.getDescribe())>0;
        if(isExist)
            return ServerResponse.createByErrorMessage("添加功能失败，该功能已存在");
        innocentMapper.insertFunction(function);
        Integer id = function.getId();
        if(id != null){
            function = innocentMapper.selectFunctionById(id);
            return ServerResponse.createBySuccess("添加功能成功",function);
        }else {
            return ServerResponse.createByErrorMessage("添加功能失败");
        }
    }

    @Override
    public ServerResponse getFunctionList(){
        List<Function> list = innocentMapper.selectFunction();
        return ServerResponse.createBySuccess("获取功能成功",list);
    }
}
