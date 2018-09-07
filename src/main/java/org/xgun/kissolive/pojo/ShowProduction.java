package org.xgun.kissolive.pojo;

public class ShowProduction {
    private Integer brandId;

    private Integer productionId;

    public ShowProduction(Integer brandId, Integer productionId) {
        this.brandId = brandId;
        this.productionId = productionId;
    }

    public ShowProduction() {
        super();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }
}