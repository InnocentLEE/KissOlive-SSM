package org.xgun.kissolive.pojo;

public class ProductionFunctionHotspot {
    private Integer hotspotId;

    private Integer productionId;

    public ProductionFunctionHotspot(Integer hotspotId, Integer productionId) {
        this.hotspotId = hotspotId;
        this.productionId = productionId;
    }

    public ProductionFunctionHotspot() {
        super();
    }

    public Integer getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(Integer hotspotId) {
        this.hotspotId = hotspotId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }
}