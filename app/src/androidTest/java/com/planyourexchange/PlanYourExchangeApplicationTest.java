package com.planyourexchange;

import android.app.Application;
import android.content.SharedPreferences;
import android.test.ApplicationTestCase;

import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.app.PlanYourExchangeModule;
import com.securepreferences.SecurePreferences;

import java.lang.Exception;
import java.lang.Override;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class PlanYourExchangeApplicationTest extends ApplicationTestCase<PlanYourExchangeApplication> {

    PlanYourExchangeApplication planYourExchangeApplication;
    PlanYourExchangeModule planYourExchangeModule;

    public PlanYourExchangeApplicationTest() {
        super(PlanYourExchangeApplication.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        planYourExchangeApplication = getApplication();
        planYourExchangeModule = new PlanYourExchangeModule(planYourExchangeApplication);
    }

    public void testPreconditions() {
        assertNotNull("planYourExchangeApplication is null",planYourExchangeApplication);
    }

    public void testSecureTestProperties() {
        SharedPreferences preferences = new SecurePreferences(planYourExchangeApplication,"","secret.xml");

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("test","secret_test");
        editor.commit();

        assertEquals("secret_test",preferences.getString("test",null));
    }

}