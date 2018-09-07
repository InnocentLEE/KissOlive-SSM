package org.xgun.kissolive.pojo;

public class ProductionFunction {
    private Integer functionId;

    private Integer productionId;

    public ProductionFunction(Integer functionId, Integer productionId) {
        this.functionId = functionId;
        this.productionId = productionId;
    }

    public ProductionFunction() {
        super();
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }
}