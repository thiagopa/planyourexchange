package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
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
