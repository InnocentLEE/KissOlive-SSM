package org.xgun.kissolive.pojo;

import java.util.Date;

public class OrderItemShipment {
    private Integer orderItemId;

    private Integer stockId;

    private Integer number;

    private Date updatetime;

    public OrderItemShipment(Integer orderItemId, Integer stockId, Integer number, Date updatetime) {
        this.orderItemId = orderItemId;
        this.stockId = stockId;
        this.number = number;
        this.updatetime = updatetime;
    }

    public OrderItemShipment() {
        super();
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}