package org.xgun.kissolive.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityGoods {
    private Integer activityId;

    private Integer goodsId;

    private BigDecimal price;

    private Date updatetime;

    public ActivityGoods(Integer activityId, Integer goodsId, BigDecimal price, Date updatetime) {
        this.activityId = activityId;
        this.goodsId = goodsId;
        this.price = price;
        this.updatetime = updatetime;
    }

    public ActivityGoods() {
        super();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}