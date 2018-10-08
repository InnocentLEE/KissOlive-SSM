package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.Brand;

import java.util.List;

/**
 * Created by GvG on 2018/10/1.
 */
public class BrandProductionShopInfo extends Brand {

    String year;

    String month;

    Integer productionNum;

    List<ProductionShopInfo> productionList;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getProductionNum() {
        return productionNum;
    }

    public void setProductionNum(Integer productionNum) {
        this.productionNum = productionNum;
    }

    public List<ProductionShopInfo> getProductionList() {
        return productionList;
    }

    public void setProductionList(List<ProductionShopInfo> productionList) {
        this.productionList = productionList;
    }
}
