package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.VipLevel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by GvG on 2018/9/12.
 */
public class ActivityMenuInfo {

    private Integer activityId;

    private String title;

    private List<VipLevel> vipList;

    private Date beginTime;

    private Date endTime;


    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VipLevel> getVipList() {
        return vipList;
    }

    public void setVipList(List<VipLevel> vipList) {
        this.vipList = vipList;
    }

    public String getBeginTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(beginTime);
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(endTime);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}
