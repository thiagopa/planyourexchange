package com.planyourexchange.utils;

import android.content.SharedPreferences;

import com.planyourexchange.rest.model.AuthToken;
import com.planyourexchange.rest.service.TokenManager;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static com.planyourexchange.utils.Constants.AUTHORIZATION;
import static com.planyourexchange.utils.Constants.TOKEN;

import static org.mockito.Mockito.*;


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
@RunWith(MockitoJUnitRunner.class)
public class TokenManagerTest {

    static final Object LOCK = new Object();

    @Mock
    SharedPreferences sharedPreferences;

    @Mock
    SharedPreferences.Editor editor;

    @Before
    public void setUp() {
        when(sharedPreferences.edit()).thenReturn(editor);
    }

    @Test(timeout = 1000)
    public void test_authenticate_before_success() throws Exception {

        final TokenManager tokenManager = new TokenManager(sharedPreferences);
        final AuthToken authToken = new AuthToken();
        authToken.setToken("NICE TOKEN");

        final Response response = new Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder()
                        .url("http://api.example.com")
                        .build())
                .code(404)
                .build();


        tokenManager.setTokenAction(new TokenManager.TokenAction() {
            @Override
            public void newToken() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tokenManager.success(authToken, null);
                    }
                }).start();

            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request result = tokenManager.authenticate(null, response);

                    String header = result.header(AUTHORIZATION);

                    assertEquals(TOKEN + authToken.getToken(), header);

                } catch (IOException e) {
                    fail("Couldn't Authenticate");
                }

                synchronized (LOCK) {
                    LOCK.notifyAll();
                }

            }
        }).start();



        synchronized (LOCK) {
            LOCK.wait();
        }
    }
}
