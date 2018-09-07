package org.xgun.kissolive.pojo;

public class ActivityVip {
    private Integer activityId;

    private Integer vipId;

    public ActivityVip(Integer activityId, Integer vipId) {
        this.activityId = activityId;
        this.vipId = vipId;
    }

    public ActivityVip() {
        super();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }
}