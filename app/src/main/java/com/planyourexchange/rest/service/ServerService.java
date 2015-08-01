package com.planyourexchange.rest.service;

import com.planyourexchange.rest.api.ServerApi;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by thiago on 28/07/15.
 */
public final class ServerService {

    private ServerApi serverApi;
    private TokenManager tokenManager;

    public ServerService(String serviceUrl, String userName, String password) {

        tokenManager = new TokenManager(userName,password);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(serviceUrl)
                .setRequestInterceptor(tokenManager).build();

        this.serverApi = restAdapter.create(ServerApi.class);
    }

    public ServerApi getServerApi() {

        if(!tokenManager.isValidToken()) {
          tokenManager.newToken(serverApi);
        }
        return serverApi;
    }
}
