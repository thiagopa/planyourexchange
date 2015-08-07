package com.planyourexchange.rest.service;

import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.model.AuthToken;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;

import org.apache.http.auth.AuthenticationException;

import java.io.IOException;
import java.net.Proxy;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by thiago on 01/08/15.
 */
public class TokenManager implements RequestInterceptor , Callback<AuthToken>, Authenticator {

    private static final String TOKEN = "Token ";
    private static final String AUTHORIZATION = "Authorization";

    private String authToken;

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

    @Override
    public void success(AuthToken authToken, Response response) {
        this.authToken = TOKEN + authToken.getToken();
    }

    @Override
    public void failure(RetrofitError error) {
        throw new RuntimeException("Can't continue without an application token",error);
    }

    @Override
    public Request authenticate(Proxy proxy, com.squareup.okhttp.Response response) throws IOException {
        if (isValidToken()) {
            return response.request().newBuilder().header(AUTHORIZATION, authToken).build();
        }
        return null;
    }

    @Override
    public Request authenticateProxy(Proxy proxy, com.squareup.okhttp.Response response) throws IOException {
        return null;
    }
}
