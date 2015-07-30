package com.planyourexchange.rest.model;

/**
 * Created by thiago on 29/07/15.
 */
public class City extends BaseModel {

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
