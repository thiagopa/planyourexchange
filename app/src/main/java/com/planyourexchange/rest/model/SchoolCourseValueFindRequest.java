package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thiago on 03/08/15.
 */
public class SchoolCourseValueFindRequest {

    @SerializedName("city_id")
    private Integer cityId;
    @SerializedName("course_id")
    private Integer courseId;
    @SerializedName("school_id")
    private Integer schoolId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}
