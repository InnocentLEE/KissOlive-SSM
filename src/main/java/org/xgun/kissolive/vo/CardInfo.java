package org.xgun.kissolive.vo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GvG on 2018/9/10.
 */
public class CardInfo {

    //购物车表ID
    private Integer cardId;

    //商品ID
    private Integer goodsId;

    //商品数量
    private Integer num;

    //商品对应产品图片路径
    private String imgUrl;

    //产品名
    private String brandName;

    //品牌名
    private String productionName;

    //对应色号名
    private String colorName;

    //色号值
    private String colorCode;

    //单价
    private BigDecimal price;

    //总价
    private BigDecimal totalPrice;

    //商品是否在在售0下架1在售
    private Integer status;

    //更新时间
    private Date updateTime;

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
        if(this.price != null) {
            this.totalPrice = this.price.multiply(new BigDecimal(this.num));
        }
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductionName() {
        return productionName;
    }

    public void setProductionName(String productionName) {
        this.productionName = productionName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2);//保留两位数
        if(this.num != null) {
            this.totalPrice = price.multiply(new BigDecimal(this.num));
        }
    }

    public BigDecimal getTotalPrice() {
        return totalPrice.setScale(2);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUpdateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(updateTime);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
