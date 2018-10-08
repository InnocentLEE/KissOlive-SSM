package org.xgun.kissolive.vo;

import org.xgun.kissolive.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Lee on 2018/10/8.
 */
public class ProductionDetails {
    private Integer id;

    private String name;

    private String description;

    private String imgUrl;

    private String detail;

    private Brand brand;

    private Origin origin;

    private MarketTime marketTime;

    private List<Hotspot> hotspots;

    private List<Function> functions;

    private List<Skin> skins;

    private List<Goods> goodses;

    public ProductionDetails() {
    }

    public ProductionDetails(Integer id, String name, String description, String imgUrl, String detail, Brand brand, Origin origin, MarketTime marketTime, List<Hotspot> hotspots, List<Function> functions, List<Skin> skins, List<Goods> goodses) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.brand = brand;
        this.origin = origin;
        this.marketTime = marketTime;
        this.hotspots = hotspots;
        this.functions = functions;
        this.skins = skins;
        this.goodses = goodses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public MarketTime getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(MarketTime marketTime) {
        this.marketTime = marketTime;
    }

    public List<Hotspot> getHotspots() {
        return hotspots;
    }

    public void setHotspots(List<Hotspot> hotspots) {
        this.hotspots = hotspots;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<Skin> getSkins() {
        return skins;
    }

    public void setSkins(List<Skin> skins) {
        this.skins = skins;
    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }

    @Override
    public String toString() {
        return "ProductionDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", detail='" + detail + '\'' +
                ", brand=" + brand +
                ", origin=" + origin +
                ", marketTime=" + marketTime +
                ", hotspots=" + hotspots +
                ", functions=" + functions +
                ", skins=" + skins +
                ", goodses=" + goodses +
                '}';
    }
}
