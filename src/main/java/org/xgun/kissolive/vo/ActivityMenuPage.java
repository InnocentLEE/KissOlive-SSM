package org.xgun.kissolive.vo;

import java.util.List;

/**
 * Created by GvG on 2018/9/12.
 */
public class ActivityMenuPage {

    private Integer nowPage;

    private Integer totalPage;

    private Integer totalNum;

    private Integer showNum;

    List<ActivityMenuInfo> activityList;

    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getShowNum() {
        return showNum;
    }

    public void setShowNum(Integer showNum) {
        this.showNum = showNum;
    }

    public List<ActivityMenuInfo> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<ActivityMenuInfo> activityList) {
        this.activityList = activityList;
    }
}
