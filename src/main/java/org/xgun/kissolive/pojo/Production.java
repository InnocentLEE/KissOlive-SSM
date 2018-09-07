package org.xgun.kissolive.pojo;

import java.util.Date;

public class Production {
    private Integer id;

    private Integer brandId;

    private Integer originId;

    private Integer marketTimeId;

    private String name;

    private String description;

    private String imgUrl;

    private String detail;

    private String search;

    private Date updatetime;

    public Production(Integer id, Integer brandId, Integer originId, Integer marketTimeId, String name, String description, String imgUrl, String detail, String search, Date updatetime) {
        this.id = id;
        this.brandId = brandId;
        this.originId = originId;
        this.marketTimeId = marketTimeId;
        this.name = name;
        this.description = description;
        this.imgUrl = imgUrl;
        this.detail = detail;
        this.search = search;
        this.updatetime = updatetime;
    }

    public Production() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getMarketTimeId() {
        return marketTimeId;
    }

    public void setMarketTimeId(Integer marketTimeId) {
        this.marketTimeId = marketTimeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search == null ? null : search.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}