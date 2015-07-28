package com.planyourexchange.rest.service;

import com.planyourexchange.PropertyReader;
import com.planyourexchange.rest.api.ServerApi;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by thiago on 28/07/15.
 */
public class ServerService {

    private ServerApi serverApi;
    private String authToken;

    public ServerService(String serviceUrl, String userName, String password) {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(serviceUrl)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        if (authToken != null) {
                            request.addHeader("Authorization", authToken);
                        }
                    }
                }).build();

        this.serverApi = restAdapter.create(ServerApi.class);

        this.authToken = this.serverApi.login(userName,password);
    }

    public ServerApi getServerApi() {
        return serverApi;
    }
}
