package org.xgun.kissolive.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
    private Integer id;

    private Integer productionId;

    private String colorCode;

    private String colorName;

    private BigDecimal price;

    private Integer status;

    private Date updatetime;

    public Goods(Integer id, Integer productionId, String colorCode, String colorName, BigDecimal price, Integer status, Date updatetime) {
        this.id = id;
        this.productionId = productionId;
        this.colorCode = colorCode;
        this.colorName = colorName;
        this.price = price;
        this.status = status;
        this.updatetime = updatetime;
    }

    public Goods() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductionId() {
        return productionId;
    }

    public void setProductionId(Integer productionId) {
        this.productionId = productionId;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode == null ? null : colorCode.trim();
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", productionId=" + productionId +
                ", colorCode='" + colorCode + '\'' +
                ", colorName='" + colorName + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", updatetime=" + updatetime +
                '}';
    }
}