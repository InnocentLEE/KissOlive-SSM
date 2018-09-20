package org.xgun.kissolive.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity {
    private Integer id;

    private String title;

    private String imgUrl;

    private String detail;

    private Date begintime;

    private Date endtime;

    private Date updatetime;

    public Activity(Integer id, String imgUrl, String detail, Date begintime, Date endtime, Date updatetime) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.begintime = begintime;
        this.endtime = endtime;
        this.updatetime = updatetime;
    }

    public Activity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getBegintime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(begintime);
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(endtime);
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getUpdatetime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(updatetime);
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}