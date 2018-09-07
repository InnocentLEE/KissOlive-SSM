package org.xgun.kissolive.pojo;

public class Brand {
    private Integer id;

    private String name;

    private String imgUrl;

    private Integer status;

    public Brand(Integer id, String name, String imgUrl, Integer status) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public Brand() {
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}