package com.planyourexchange.utils;

import com.planyourexchange.rest.model.AuthToken;
import com.planyourexchange.rest.service.TokenManager;
import com.squareup.okhttp.Protocol;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.Proxy;

import static org.junit.Assert.*;

/**
 * Created by thiago on 07/08/15.
 */
public class TokenManagerTest {

    static final Object LOCK = new Object();

    @Test(timeout = 1000)
    public void test_authenticate_before_success() throws Exception {

        final TokenManager tokenManager = new TokenManager();
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

                    String header = result.header(TokenManager.AUTHORIZATION);

                    assertEquals(TokenManager.TOKEN + authToken.getToken(), header);

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
