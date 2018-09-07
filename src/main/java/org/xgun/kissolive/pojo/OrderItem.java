package org.xgun.kissolive.pojo;

import java.util.Date;

public class OrderItem {
    private Integer id;

    private Integer orderId;

    private Integer goodsId;

    private Integer number;

    private Date updatetime;

    public OrderItem(Integer id, Integer orderId, Integer goodsId, Integer number, Date updatetime) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.number = number;
        this.updatetime = updatetime;
    }

    public OrderItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
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