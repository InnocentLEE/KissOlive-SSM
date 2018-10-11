package org.xgun.kissolive.service;

import org.xgun.kissolive.common.ServerResponse;
import org.xgun.kissolive.pojo.Stock;
import org.xgun.kissolive.pojo.Supplier;
import org.xgun.kissolive.vo.ListOrder;
import org.xgun.kissolive.vo.ListOrderItem;
import org.xgun.kissolive.vo.ListStock;

import java.util.List;

public interface ISNH48Service {

    ServerResponse<List<Supplier>> listSupplier();

    ServerResponse addSupplier(String name);

    ServerResponse removeSupplier(Integer id);

    ServerResponse updateSupplier(Integer id, String name);

    ServerResponse addStock(Stock stock);

    ServerResponse<List<ListStock>> listStock();

    ServerResponse<ListOrder> addOrder(ListOrderItem OrderItems);

    ServerResponse updateOrderStatus(Integer orderID, Integer status);
}
