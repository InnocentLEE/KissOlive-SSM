package org.xgun.kissolive.pojo;

import java.util.Date;

public class Assess {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Integer productionId;

    private String content;

    private Date updatetime;

    public Assess(Integer id, Integer userId, Integer goodsId, Integer productionId, String content, Date updatetime) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.productionId = productionId;
        this.content = content;
        this.updatetime = updatetime;
    }

    public Assess() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}