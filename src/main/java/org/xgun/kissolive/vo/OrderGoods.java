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

    public OrderGoods(OrderItem orderitem, String name, String url) {
        this.name = name;
        this.url = url;
        this.setPrice(orderitem.getPrice());
        this.setOrderId(orderitem.getOrderId());
        this.setGoodsId(orderitem.getGoodsId());
        this.setId(orderitem.getId());
        this.setNumber(orderitem.getNumber());
        this.setUpdatetime(orderitem.getUpdatetime());
    }
    private String name;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
