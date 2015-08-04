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
    private BigDecimal weekPrice;
    private String weekPriceCurrency;

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

    public String getWeekPriceCurrency() {
        return weekPriceCurrency;
    }

    public void setWeekPriceCurrency(String weekPriceCurrency) {
        this.weekPriceCurrency = weekPriceCurrency;
    }
}
