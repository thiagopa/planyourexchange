package com.planyourexchange.rest.api;

import com.planyourexchange.rest.model.AuthToken;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.CostOfLiving;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.HealthInsurance;
import com.planyourexchange.rest.model.School;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

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
public interface ServerApi {

    @FormUrlEncoded
    @POST("/token-auth/")
    void login(@Field("username") String userName, @Field("password") String password,Callback<AuthToken > callback);

    @GET("/countries/")
    void listCountries(Callback<List<Country>> callback);

    @GET("/countries/{id}/cities/")
    void listCities(@Path("id") Integer countryId,Callback<List<City>> callback);

    @GET("/countries/{id}/healthinsurances/")
    void listHealthInsurances(@Path("id") Integer countryId,Callback<List<HealthInsurance>> callback);

    @GET("/cities/{id}/courses/")
    void listCourses(@Path("id") Integer cityId,Callback<List<Course>> callback);

    @GET("/cities/{id}/schools/")
    void listSchools(@Path("id") Integer cityId,Callback<List<School>> callback);

    @GET("/cities/{id}/costofliving/")
    void getCostOfLiving(@Path("id") Integer cityId,Callback<CostOfLiving> callback);

    @POST("/schoolcoursevalue/find/")
    void findCourseSchoolValue(@Body SchoolCourseValueKey request,Callback<List<SchoolCourseValue>> callback);
}
