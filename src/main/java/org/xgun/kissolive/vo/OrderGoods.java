package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.OrderItem;

/**
 * 订单商品项多一个名字字段
 */
public class OrderGoods extends OrderItem {

    public OrderGoods() {}
    public OrderGoods(OrderItem orderitem, String name) {
        this.name = name;
        this.setPrice(orderitem.getPrice());
        this.setOrderId(orderitem.getOrderId());
        this.setGoodsId(orderitem.getGoodsId());
        this.setId(orderitem.getId());
        this.setNumber(orderitem.getNumber());
        this.setUpdatetime(orderitem.getUpdatetime());
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
