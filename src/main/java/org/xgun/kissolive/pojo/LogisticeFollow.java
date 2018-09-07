package org.xgun.kissolive.pojo;

import java.util.Date;

public class LogisticeFollow {
    private Integer orderId;

    private String content;

    private Date time;

    public LogisticeFollow(Integer orderId, String content, Date time) {
        this.orderId = orderId;
        this.content = content;
        this.time = time;
    }

    public LogisticeFollow() {
        super();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}