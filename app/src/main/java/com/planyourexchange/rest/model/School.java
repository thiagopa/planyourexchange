package com.planyourexchange.rest.model;

/**
 * Created by thiago on 29/07/15.
 */
public class School extends BaseModel {
    private City city;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
