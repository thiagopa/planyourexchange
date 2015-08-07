package com.planyourexchange.rest.api;

import com.planyourexchange.rest.model.AuthToken;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Course;
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
 * Created by thiago on 28/07/15.
 */
public interface ServerApi {

    @FormUrlEncoded
    @POST("/token-auth/")
    AuthToken login(@Field("username") String userName, @Field("password") String password);

    @GET("/countries/")
    void listCountries(Callback<List<Country>> callback);

    @GET("/countries/{id}/cities/")
    void listCities(@Path("id") Integer countryId,Callback<List<City>> callback);

    @GET("/cities/{id}/courses/")
    void listCourses(@Path("id") Integer cityId,Callback<List<Course>> callback);

    @GET("/cities/{id}/schools/")
    void listSchools(@Path("id") Integer cityId,Callback<List<School>> callback);

    @POST("/schoolcoursevalue/find/")
    void findCourseSchoolValue(@Body SchoolCourseValueKey request,Callback<List<SchoolCourseValue>> callback);
}
