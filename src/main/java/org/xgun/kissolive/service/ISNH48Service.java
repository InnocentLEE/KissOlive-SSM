package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Permit;
import org.xgun.kissolive.pojo.Stock;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.pojo.VipLevel;
import org.xgun.kissolive.vo.ListOrder;
import org.xgun.kissolive.vo.ListOrderItem;
import org.xgun.kissolive.vo.ListStock;
import org.xgun.kissolive.vo.OrderAddress;

import java.util.List;

public interface ISNH48Service {

    ServerResponse<List<Supplier>> listSupplier();

    ServerResponse addSupplier(String name);

    ServerResponse removeSupplier(Integer id);

    ServerResponse updateSupplier(Integer id, String name);

    ServerResponse addStock(Stock stock);

    ServerResponse<List<ListStock>> listStock();

    ServerResponse<ListOrder> addOrder(ListOrderItem OrderItems, Integer userID);

    ServerResponse<ListOrder> getListOrder(Integer orderID);

    ServerResponse updateOrderAddress(Integer orderID, Integer addressID);

    ServerResponse updateOrderStatus(Integer orderID, Integer status);

    ServerResponse cancelOrder(Integer orderID);

    ServerResponse<List<ListOrder>> getOrders(Integer status, Integer page, Integer size, Integer userID);

    ServerResponse<List<ListOrder>> getAdminOrders(Integer status, Integer page, Integer size);

    ServerResponse<List<Permit>> getPermits();

    ServerResponse updatePermit(Permit permit);

    //判断用户是否拥有权限（0：homeManage；1：brandManage；2：goodsManage；3：stockManage；4：orderManage；5：activityManage）
    boolean hasPermit(Integer userID, Integer permitCode);

    ServerResponse<List<VipLevel>> getVipLevel();

    ServerResponse updateVipLevel(VipLevel vipLevel);

    //获取用户的VIP等级信息
    VipLevel getVIPLevel(Integer userID);

    ServerResponse<OrderAddress> getOrderAddress(Integer orderID);
}
