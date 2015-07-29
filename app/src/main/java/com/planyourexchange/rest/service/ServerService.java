package com.planyourexchange.rest.service;

import com.planyourexchange.PropertyReader;
import com.planyourexchange.rest.api.ServerApi;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by thiago on 28/07/15.
 */
public class ServerService {

    private static final String TOKEN = "Token ";
    private static final String AUTHORIZATION = "Authorization";

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
                            request.addHeader(AUTHORIZATION, authToken);
                        }
                    }
                }).build();

        this.serverApi = restAdapter.create(ServerApi.class);

        this.authToken = TOKEN + this.serverApi.login(userName,password).getToken();
    }

    public ServerApi getServerApi() {
        return serverApi;
    }
}
