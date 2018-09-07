package org.xgun.kissolive.pojo;

public class Permit {
    private Integer userId;

    private Integer homeManage;

    private Integer brandManage;

    private Integer goodsManage;

    private Integer stockManage;

    private Integer orderManage;

    private Integer activityManage;

    public Permit(Integer userId, Integer homeManage, Integer brandManage, Integer goodsManage, Integer stockManage, Integer orderManage, Integer activityManage) {
        this.userId = userId;
        this.homeManage = homeManage;
        this.brandManage = brandManage;
        this.goodsManage = goodsManage;
        this.stockManage = stockManage;
        this.orderManage = orderManage;
        this.activityManage = activityManage;
    }

    public Permit() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHomeManage() {
        return homeManage;
    }

    public void setHomeManage(Integer homeManage) {
        this.homeManage = homeManage;
    }

    public Integer getBrandManage() {
        return brandManage;
    }

    public void setBrandManage(Integer brandManage) {
        this.brandManage = brandManage;
    }

    public Integer getGoodsManage() {
        return goodsManage;
    }

    public void setGoodsManage(Integer goodsManage) {
        this.goodsManage = goodsManage;
    }

    public Integer getStockManage() {
        return stockManage;
    }

    public void setStockManage(Integer stockManage) {
        this.stockManage = stockManage;
    }

    public Integer getOrderManage() {
        return orderManage;
    }

    public void setOrderManage(Integer orderManage) {
        this.orderManage = orderManage;
    }

    public Integer getActivityManage() {
        return activityManage;
    }

    public void setActivityManage(Integer activityManage) {
        this.activityManage = activityManage;
    }
}