package org.xgun.kissolive.pojo;

public class VipLevel {
    private Integer id;

    private String name;

    private Integer scoreLow;

    private Integer scoreHigh;

    private String logoUrl;

    public VipLevel(Integer id, String name, Integer scoreLow, Integer scoreHigh, String logoUrl) {
        this.id = id;
        this.name = name;
        this.scoreLow = scoreLow;
        this.scoreHigh = scoreHigh;
        this.logoUrl = logoUrl;
    }

    public VipLevel() {
        super();
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
        this.name = name == null ? null : name.trim();
    }

    public Integer getScoreLow() {
        return scoreLow;
    }

    public void setScoreLow(Integer scoreLow) {
        this.scoreLow = scoreLow;
    }

    public Integer getScoreHigh() {
        return scoreHigh;
    }

    public void setScoreHigh(Integer scoreHigh) {
        this.scoreHigh = scoreHigh;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }
}