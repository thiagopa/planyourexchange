package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by thiago on 29/07/15.
 */
public class SchoolCourseValue {
    private Course course;
    private School school;
    private String url;
    @SerializedName("week_price")
    private BigDecimal weekPrice;
    @SerializedName("week_price_currency")
    private String currency;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getWeekPrice() {
        return weekPrice;
    }

    public void setWeekPrice(BigDecimal weekPrice) {
        this.weekPrice = weekPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
