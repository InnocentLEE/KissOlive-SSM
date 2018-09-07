package org.xgun.kissolive.pojo;

public class ProductionFunctionSkin {
    private Integer skinId;

    private Integer productionId;

    public ProductionFunctionSkin(Integer skinId, Integer productionId) {
        this.skinId = skinId;
        this.productionId = productionId;
    }

    public ProductionFunctionSkin() {
        super();
    }

    public Integer getSkinId() {
        return skinId;
    }

    public void setSkinId(Integer skinId) {
        this.skinId = skinId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }
}