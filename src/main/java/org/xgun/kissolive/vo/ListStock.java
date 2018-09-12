package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.Stock;

public class ListStock extends Stock {

    private String productionName;

    private String supplierName;

    private String stringShelfdate;

    public String getStringShelfdate() {
        return stringShelfdate;
    }

    public void setStringShelfdate(String stringShelfdate) {
        this.stringShelfdate = stringShelfdate;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
