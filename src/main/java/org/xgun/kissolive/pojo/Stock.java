package org.xgun.kissolive.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Stock {
    private Integer id;

    private String stockId;

    private Integer goodsId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shelfdate;

    private Integer number;

    private Integer supplierId;

    private Date updatetime;

    public Stock(Integer id, String stockId, Integer goodsId, Date shelfdate, Integer number, Integer supplierId, Date updatetime) {
        this.id = id;
        this.stockId = stockId;
        this.goodsId = goodsId;
        this.shelfdate = shelfdate;
        this.number = number;
        this.supplierId = supplierId;
        this.updatetime = updatetime;
    }

    public Stock() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId == null ? null : stockId.trim();
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Date getShelfdate() {
        return shelfdate;
    }

    public void setShelfdate(Date shelfdate) {
        this.shelfdate = shelfdate;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}