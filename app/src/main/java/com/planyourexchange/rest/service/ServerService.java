package com.planyourexchange.rest.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.planyourexchange.rest.api.ServerApi;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by thiago on 28/07/15.
 */
public final class ServerService {

    public final ServerApi serverApi;

    public ServerService(String serviceUrl, final String userName, final String password) {

        final TokenManager tokenManager = new TokenManager();

        // -- Saving a lot of config
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        // -- For Token Authentication Purposes
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(tokenManager);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setEndpoint(serviceUrl)
                .setRequestInterceptor(tokenManager)
                .setClient(new OkClient(okHttpClient)).build();

        this.serverApi = restAdapter.create(ServerApi.class);

        // -- Call login to get a new Token only WHEN it is needed
        tokenManager.setTokenAction(new TokenManager.TokenAction() {
            @Override
            public void newToken() {
                // -- Getting Authentication Token
                serverApi.login(userName, password, tokenManager);
            }
        });
    }
}
