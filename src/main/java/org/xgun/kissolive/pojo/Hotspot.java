package org.xgun.kissolive.pojo;

public class Hotspot {
    private Integer id;

    private String describe;

    public Hotspot(Integer id, String describe) {
        this.id = id;
        this.describe = describe;
    }

    public Hotspot() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}