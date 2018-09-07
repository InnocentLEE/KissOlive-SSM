package org.xgun.kissolive.pojo;

public class Supplier {
    private Integer id;

    private String name;

    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Supplier() {
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
}