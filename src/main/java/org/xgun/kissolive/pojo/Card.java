package org.xgun.kissolive.pojo;

import java.util.Date;

public class Card {
    private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Integer numberId;

    private Date updatetime;

    public Card(Integer id, Integer userId, Integer goodsId, Integer numberId, Date updatetime) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.numberId = numberId;
        this.updatetime = updatetime;
    }

    public Card() {
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

    public Integer getNumberId() {
        return numberId;
    }

    public void setNumberId(Integer numberId) {
        this.numberId = numberId;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}