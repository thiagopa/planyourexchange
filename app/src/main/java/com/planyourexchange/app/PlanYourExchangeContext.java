package com.planyourexchange.app;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.utils.PropertyReader;

/**
 * Created by thiago on 31/07/15.
 *
 * Encapsulates all objects that need to be accessed in the app Context
 */
public final class PlanYourExchangeContext {

    private static PlanYourExchangeContext instance;

    public final PropertyReader propertyReader;
    public final GoogleAnalytics googleAnalytics;
    public final Tracker tracker;
    public final ServerApi serverApi;

    public PlanYourExchangeContext(PropertyReader propertyReader, GoogleAnalytics googleAnalytics, Tracker tracker, ServerApi serverApi) {
        this.propertyReader = propertyReader;
        this.googleAnalytics = googleAnalytics;
        this.tracker = tracker;
        this.serverApi = serverApi;

        this.instance = this;
    }

    public static PlanYourExchangeContext getInstance() {
        return instance;
    }
}
