package com.planyourexchange.rest.service;

import com.planyourexchange.rest.api.ServerApi;

import retrofit.RequestInterceptor;

/**
 * Created by thiago on 01/08/15.
 */
public class TokenManager implements RequestInterceptor {

    private static final String TOKEN = "Token ";
    private static final String AUTHORIZATION = "Authorization";

    private final String userName;
    private final String password;
    private String authToken;

    public TokenManager(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void intercept(RequestFacade request) {
        if (isValidToken()) {
            request.addHeader(AUTHORIZATION, authToken);
        }
    }

    // -- For while just verify if it exists
    public boolean isValidToken() {
        return authToken != null;
    }

    // -- Get a new token
    public void newToken(ServerApi serverApi) {
        this.authToken = TOKEN + serverApi.login(userName,password).getToken();
    }
}
