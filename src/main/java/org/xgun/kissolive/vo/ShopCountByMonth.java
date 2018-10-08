package org.xgun.kissolive.vo;

/**
 * Created by GvG on 2018/10/8.
 */
public class ShopCountByMonth {

    private String month;

    private Integer num;

    public ShopCountByMonth(String month, Integer num){
        this.month = month;
        this.num = num;
    }

    public ShopCountByMonth(){
        super();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
