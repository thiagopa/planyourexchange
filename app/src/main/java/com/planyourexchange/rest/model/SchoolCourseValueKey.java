package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by thiago on 03/08/15.
 */
public class SchoolCourseValueKey implements Serializable {

    private Integer cityId;
    private Integer courseId;
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


    @Override
    public boolean equals(Object o) {

        if (o != null && o instanceof SchoolCourseValueKey) {
            SchoolCourseValueKey c = (SchoolCourseValueKey) o;
            return cityId != null && cityId.equals(c.cityId) && ((courseId != null && courseId.equals(c.courseId)) || (schoolId != null && schoolId.equals(c.schoolId)));
        }

        return false;
    }

    @Override
    public int hashCode() {
        return cityId != null ?
                courseId != null ?
                        cityId.intValue() + courseId.intValue() :
                        schoolId != null ?
                                cityId.intValue() + schoolId.intValue() :
                                cityId.intValue() : -1;
    }
}
