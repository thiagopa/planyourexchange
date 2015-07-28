package com.planyourexchange.rest.api;

import com.planyourexchange.rest.model.Country;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by thiago on 28/07/15.
 */
public interface ServerApi {

    @FormUrlEncoded
    @POST("/token-auth/")
    String login(@Field("username") String userName, @Field("password") String password);

    @GET("/cities/")
    List<Country> listCountries();
}
