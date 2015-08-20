package com.planyourexchange.rest.service;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.planyourexchange.rest.api.ServerApi;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

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
public final class ServerService {

    public final ServerApi serverApi;

    public ServerService(String serviceUrl, final String userName, final String password) {

        final TokenManager tokenManager = new TokenManager();

        // -- Initializing gson factory with joda converters and underscores default policy
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        Converters.registerLocalDate(builder);
        Converters.registerLocalTime(builder);

        // -- For Token Authentication Purposes
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setAuthenticator(tokenManager);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(builder.create()))
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
