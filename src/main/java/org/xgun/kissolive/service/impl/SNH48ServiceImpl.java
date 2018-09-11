package org.xgun.kissolive.service.impl;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.dao.SNH48Mapper;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.service.ISNH48Service;

import java.util.List;

@Service("iSNH48Service")
public class SNH48ServiceImpl implements ISNH48Service {

    @Autowired
    private SNH48Mapper mapper;

    @Override
    public ServerResponse<List<Supplier>> listSupplier() {

        List<Supplier> listSupplier = mapper.listSupplier();
        if (listSupplier == null || listSupplier.size() == 0)
            return ServerResponse.createBySuccess("供应商为空",null);
        return ServerResponse.createBySuccess(listSupplier);
    }

    @Override
    public ServerResponse addSupplier(String name) {
        if (mapper.addSupplier(name) > 0)
            return ServerResponse.createBySuccess("添加供应商成功");
        return ServerResponse.createByErrorMessage("添加供应商失败");
    }

    @Override
    public ServerResponse removeSupplier(Integer id) {
        if (mapper.removeSupplier(id) > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByErrorMessage("删除供应商失败");
    }

    @Override
    public ServerResponse updateSupplier(Integer id, String name) {

        if (mapper.updateSupplier(id, name) > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByErrorMessage("修改供应商信息失败");
    }
}
