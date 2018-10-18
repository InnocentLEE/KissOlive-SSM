package org.xgun.kissolive.pojo;

import org.xgun.kissolive.utils.DateTimeUtil;
import org.xgun.kissolive.utils.DateUtil;

import java.util.Date;

public class ChatMessage {
    private Integer id;

    private String message;

    private Integer status;

    private Integer userId;

    private Integer source;

    private Date updatetime;

    public ChatMessage(Integer id, String message, Integer status, Integer userId, Integer source, Date updatetime) {
        this.id = id;
        this.message = message;
        this.status = status;
        this.userId = userId;
        this.source = source;
        this.updatetime = updatetime;
    }

    public ChatMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getUpdatetime() {
        return DateTimeUtil.dateToStr(this.updatetime,"yyyy-MM-dd hh:mm:ss");
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}