package org.xgun.kissolive.pojo;

/**
 * Created by Lee on 2018/8/31.
 */
public class Demo {
    private int id;
    private String name;

    public Demo() {
    }

    public Demo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Demo)) return false;

        Demo demo = (Demo) o;

        if (id != demo.id) return false;
        return name.equals(demo.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
