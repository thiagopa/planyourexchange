package com.planyourexchange.rest.model;

/**
 * Created by thiago on 29/07/15.
 */
public class BaseModel implements Comparable<BaseModel> {

    private String icon;
    private String name;
    private Integer id;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {

        if(o!=null && o instanceof BaseModel) {
            return this.id.equals(((BaseModel) o).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public int compareTo(BaseModel another) {
        return name.compareTo(another.name);
    }
}
