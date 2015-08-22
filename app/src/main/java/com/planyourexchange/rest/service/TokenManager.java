package com.planyourexchange.rest.service;

import com.planyourexchange.rest.model.AuthToken;
import com.squareup.okhttp.Authenticator;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.net.Proxy;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.planyourexchange.utils.Constants.AUTHORIZATION;
import static com.planyourexchange.utils.Constants.TOKEN;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class TokenManager implements RequestInterceptor, Callback<AuthToken>, Authenticator {

    // -- Token must be acessible through multiple threads
    private class TokenWrapper {
        public StringBuilder token;

        @Override
        public String toString() {
            return token.toString();
        }
    }

    private final TokenWrapper authToken = new TokenWrapper();

    private TokenAction tokenAction;

    @Override
    public void intercept(RequestFacade request) {
        if (isValidToken()) {
            request.addHeader(AUTHORIZATION, authToken.toString());
        }
    }

    // -- For while just verify if it exists
    public boolean isValidToken() {
        return authToken.token != null;
    }

    @Override
    public void success(AuthToken authToken, Response response) {
        // -- Writes the token and notify possible sleeping threads
        synchronized (this.authToken) {
            if(this.authToken.token == null) {
                this.authToken.token = new StringBuilder();
                this.authToken.token.append(TOKEN);
                this.authToken.token.append(authToken.getToken());
            }
            this.authToken.notify();
        }
    }

    @Override
    public void failure(RetrofitError error) {
        throw new RuntimeException("Can't continue without an application token", error);
    }

    @Override
    public Request authenticate(Proxy proxy, com.squareup.okhttp.Response response) throws IOException {
        synchronized (this.authToken) {
            // -- Sends signal to get a new token and wait until it becomes valid
            tokenAction.newToken();
            try {
                this.authToken.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response.request().newBuilder().header(AUTHORIZATION, authToken.toString()).build();
    }

    @Override
    public Request authenticateProxy(Proxy proxy, com.squareup.okhttp.Response response) throws IOException {
        return null;
    }

    // -- Future Token Actions
    // - This would easily be able to handle future expired tokens
    public interface TokenAction {
        // -- Getting a new Token
        void newToken();
    }

    public void setTokenAction(TokenAction tokenAction) {
        this.tokenAction = tokenAction;
    }
}
