package com.planyourexchange.rest.model;

/**
 * Created by thiago on 29/07/15.
 */
public class BaseModel {

    private String icon;
    private String name;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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


}