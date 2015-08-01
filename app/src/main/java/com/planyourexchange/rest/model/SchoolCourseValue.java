package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by thiago on 29/07/15.
 */
public class SchoolCourseValue {
    private Course course;
    private School school;
    private Integer id;
    @SerializedName("week_price")
    private BigDecimal weekPrice;
    @SerializedName("week_price_currency")
    private String currency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
