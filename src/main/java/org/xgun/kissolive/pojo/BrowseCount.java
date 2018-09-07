package org.xgun.kissolive.pojo;

import java.util.Date;

public class BrowseCount {
    private Integer userId;

    private Integer productionId;

    private Date time;

    public BrowseCount(Integer userId, Integer productionId, Date time) {
        this.userId = userId;
        this.productionId = productionId;
        this.time = time;
    }

    public BrowseCount() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}