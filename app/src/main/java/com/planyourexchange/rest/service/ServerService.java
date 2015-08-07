package com.planyourexchange.rest.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.planyourexchange.rest.api.ServerApi;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by thiago on 28/07/15.
 */
public final class ServerService {

    private ServerApi serverApi;

    public ServerService(String serviceUrl, String userName, String password) {

        TokenManager tokenManager = new TokenManager();

        // -- Saving a lot of config
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setEndpoint(serviceUrl)
                .setRequestInterceptor(tokenManager).build();

        this.serverApi = restAdapter.create(ServerApi.class);

        // -- Getting Authentication Token
        this.serverApi.login(userName,password,tokenManager);
    }

    public ServerApi getServerApi() {
        return serverApi;
    }
}
