package org.xgun.kissolive.vo;

/**
 * Created by Lee on 2018/10/8.
 */
public class ProductionSame {
    private Integer id;
    private String search;
    private double same;

    public ProductionSame() {
    }

    public ProductionSame(Integer id, String search, double same) {
        this.id = id;
        this.search = search;
        this.same = same;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public double getSame() {
        return same;
    }

    public void setSame(double same) {
        this.same = same;
    }

    @Override
    public String toString() {
        return "ProductionSame{" +
                "id=" + id +
                ", search='" + search + '\'' +
                ", same=" + same +
                '}';
    }
}
