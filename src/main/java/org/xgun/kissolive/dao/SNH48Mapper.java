package org.xgun.kissolive.dao;


import org.apache.ibatis.annotations.Param;
import org.xgun.kissolive.pojo.*;
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

    /**
     * 查找商品ID的所有库存信息
     * @param goodsID 商品ID
     * @return
     */
    List<Stock> listStockByGoodsID(@Param("goodsID")Integer goodsID);

    /**
     * 生成订单
     * @param order 订单信息
     * @return
     */
    int addOrder(@Param("order")Order order);

    /**
     * 更新库存
     * @param stock
     * @return
     */
    int updateStock(@Param("stock")Stock stock);

    /**
     * 存订单项目信息表
     * @param orderItem
     * @return
     */
    int insertOrderItem(@Param("orderItem")OrderItem orderItem);

    /**
     * 存订单项目出货信息
     * @param orderItemShipment
     * @return
     */
    int insertOrderItemShipment(@Param("orderItemShipment")OrderItemShipment orderItemShipment);

    String getGoodsName(@Param("goodsID")Integer goodsID);

    int updateOrderAddress(@Param("orderID")Integer orderID, @Param("addressID")Integer addressID);

    int updateOrderStatus(@Param("orderID")Integer orderID, @Param("status")Integer status);

    List<Order> listOrder(@Param("userID")Integer userID, @Param("status")Integer status);

    List<Order> listAdminOrder(@Param("status")Integer status);

    List<OrderItem> listOrderItem(@Param("orderID")Integer orderID);

    List<OrderItemShipment> listOrderItemShipment(@Param("orderItemID")Integer orderItemID);

    int recoverStock(@Param("stockID")Integer stockID, @Param("num")int num);

    int deleteOrderItemShipment(@Param("stockID")Integer stockID);

    Order getOrder(@Param("orderID")Integer orderID);

    List<Permit> listPermit();

    int updatePermit(Permit permit);

    //获取该用户的权限信息
    Permit getPermit(@Param("userID")Integer userID);

    List<VipLevel> listVipLevel();

    int updateVipLevel(VipLevel vipLevel);

    Integer getScore(@Param("userID")Integer userID);

    VipLevel getVipLevel(@Param("score")Integer score);

    String getGoodsUrl(@Param("goodsID")Integer goodsID);

    Address getAddress(@Param("addressID")Integer addressID);

}
