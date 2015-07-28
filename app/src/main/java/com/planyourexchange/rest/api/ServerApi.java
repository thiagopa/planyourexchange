package com.planyourexchange.rest.api;

import com.planyourexchange.rest.model.Country;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by thiago on 28/07/15.
 */
public interface ServerApi {
    @GET("/cities/")
    List<Country> listCountries();
}
