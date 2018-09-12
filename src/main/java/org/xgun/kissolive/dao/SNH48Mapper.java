package org.xgun.kissolive.dao;


import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.Stock;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.vo.ListStock;

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
    //添加入库信息
    Integer addStock(@Param("stock")Stock stock);
    //获取库存列表
    //XXX 修改sql语句
    List<ListStock> listStock();
}
