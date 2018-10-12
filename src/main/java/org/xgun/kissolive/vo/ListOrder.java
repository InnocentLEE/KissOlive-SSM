package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单列表
 */
public class ListOrder {
    //订单ID
    private Integer orderId;
    //订单编号
    private String orderNumber;
    //订单总价
    private BigDecimal price;
    //商品列表
    private List<OrderGoods> goods;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<OrderGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderGoods> goods) {
        this.goods = goods;
    }
}