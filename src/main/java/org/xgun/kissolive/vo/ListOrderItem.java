package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * 接收购物车下订单信息
 */
public class ListOrderItem {

    private BigDecimal price;

    private Integer addressID;

    private List<OrderItem> items;

    public ListOrderItem() {
    }

    public ListOrderItem(BigDecimal price, Integer addressID, List<OrderItem> items) {
        this.price = price;
        this.addressID = addressID;
        this.items = items;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
