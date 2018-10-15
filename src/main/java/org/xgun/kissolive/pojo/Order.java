package org.xgun.kissolive.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer id;

    private Integer addressId;

    private Integer userId;

    private String number;

    private Integer status;

    private Date time;

    private Date updatetime;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Order(Integer id, Integer addressId, Integer userId, String number, Integer status, Date time, Date updatetime, BigDecimal price) {
        this.id = id;
        this.addressId = addressId;
        this.userId = userId;
        this.number = number;
        this.status = status;
        this.time = time;
        this.updatetime = updatetime;
        this.price = price;
    }

    public Order() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", addressId=" + addressId +
                ", userId=" + userId +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", time=" + time +
                ", updatetime=" + updatetime +
                ", price=" + price +
                '}';
    }
}