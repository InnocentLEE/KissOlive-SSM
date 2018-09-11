package org.xgun.kissolive.dao;


import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Supplier;

import java.util.List;

public interface SNH48Mapper {

    //查询供应商列表
    List<Supplier> listSupplier();
    //添加一个供应商
    Integer addSupplier(@Param("name") String name);
    //删除一个供应商
    Integer removeSupplier(@Param("id") Integer id);
    //更新供应商信息
    Integer updateSupplier(@Param("id") Integer id, @Param("name") String name);
}
