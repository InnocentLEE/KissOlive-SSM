package org.xgun.kissolive.vo;

import java.math.BigDecimal;

/**
 * Created by GvG on 2018/9/20.
 */
public class SalesCountByMonth {

    private String month;

    private BigDecimal price;

    public SalesCountByMonth(String month, BigDecimal price){
        this.month = month;
        this.price = price;
    }

    public SalesCountByMonth(){
        super();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2);
    }
}
