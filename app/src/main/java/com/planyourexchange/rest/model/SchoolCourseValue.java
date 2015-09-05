package com.planyourexchange.rest.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.math.BigDecimal;

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
@Parcel
public class SchoolCourseValue implements Comparable<SchoolCourseValue>{
    Course course;
    School school;
    Integer id;

    private BigDecimal weekPrice;

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

    @Override
    public String toString() {
        return new StringBuilder()
                .append(course.getName())
                .append(" at ")
                .append(school.getName())
                .append(" costs ")
                .append(weekPrice)
                .append(" per week")
                .toString();
    }

    @Override
    public int compareTo(SchoolCourseValue another) {
        return course.getName().compareTo(another.getCourse().getName());
    }
}
