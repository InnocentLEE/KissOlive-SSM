package org.xgun.kissolive.vo;

/**
 * Created by GvG on 2018/10/13.
 */
public class Message {

    private String message;

    private String datetime;

    private Integer type; //1为用户消息，2为管理员信息

    private String from;

    private String to;

    public Message(String message, String datetime, Integer type,String from, String to) {
        this.message = message;
        this.datetime = datetime;
        this.type = type;
        this.from = from;
        this.to = to;
    }

    public Message(){
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
