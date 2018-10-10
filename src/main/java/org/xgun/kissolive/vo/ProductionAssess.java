package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.Goods;
import org.xgun.kissolive.pojo.User;

import java.util.Date;

/**
 * Created by Lee on 2018/10/10.
 */
public class ProductionAssess {
    private Integer id;
    private User user;
    private Goods goods;
    private Integer productionId;
    private String content;
    private Date updatetime;

    public ProductionAssess() {
    }

    public ProductionAssess(Integer id, User user, Goods goods, Integer productionId, String content, Date updatetime) {
        this.id = id;
        this.user = user;
        this.goods = goods;
        this.productionId = productionId;
        this.content = content;
        this.updatetime = updatetime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
        this.content = content;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "ProductionAssess{" +
                "id=" + id +
                ", user=" + user +
                ", goods=" + goods +
                ", productionId=" + productionId +
                ", content='" + content + '\'' +
                ", updatetime=" + updatetime +
                '}';
    }
}
