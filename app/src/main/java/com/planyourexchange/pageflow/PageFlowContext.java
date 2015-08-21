/*
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

package com.planyourexchange.pageflow;

import com.planyourexchange.rest.model.AirFare;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.HealthInsurance;
import com.planyourexchange.rest.model.SchoolCourseValue;

/**
 * @author Thiago Pagonha
 * @version 19/08/15.
 */
public class PageFlowContext {
    private Country country;
    private City city;
    private Course course;
    private SchoolCourseValue schoolCourseValue;
    private HealthInsurance healthInsurance;
    private AirFare airFare;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public SchoolCourseValue getSchoolCourseValue() {
        return schoolCourseValue;
    }

    public void setSchoolCourseValue(SchoolCourseValue schoolCourseValue) {
        this.schoolCourseValue = schoolCourseValue;
    }

    public HealthInsurance getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(HealthInsurance healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public void setAirFare(AirFare airFare) {
        this.airFare = airFare;
    }

    public AirFare getAirFare() {
        return airFare;
    }
}
