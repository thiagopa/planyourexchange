package com.planyourexchange.rest.api;

import com.planyourexchange.rest.model.AuthToken;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.School;
import com.planyourexchange.rest.model.SchoolCourseValue;

import java.util.List;

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
    List<Country> listCountries();

    @GET("/cities/")
    List<City> listCities();

    @GET("/countries/{id}/cities/")
    List<City> listCities(@Path("id") Integer countryId);

    @GET("/courses/")
    List<Course> listCourses();

    @GET("/schools/")
    List<School> listSchools();

    @GET("/coursevaluebyschool/")
    List<SchoolCourseValue> listCourseValueBySchool();
}
