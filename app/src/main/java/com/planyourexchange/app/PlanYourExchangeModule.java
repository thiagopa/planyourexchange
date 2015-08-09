/*
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

package com.planyourexchange.app;

import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.utils.PropertyReader;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application-wide dependencies
 * @author Thiago Pagonha
 * @version 09/08/15.
 */
@Module
public class PlanYourExchangeModule {
    // -- Configs
    private static final String TAG = "PlanYourExchangeModule";
    private static final int DISPATCH_PERIOD_IN_SECONDS = 1800;
    // -- My Application Context
    private final PlanYourExchangeApplication planYourExchangeApplication;
    // -- All dependencies
    private PropertyReader propertyReader;
    private GoogleAnalytics googleAnalytics;
    private Tracker tracker;
    private ServerApi serverApi;

    public PlanYourExchangeModule(PlanYourExchangeApplication planYourExchangeApplication) {
        this.planYourExchangeApplication = planYourExchangeApplication;

        // -- Initialize properties Reader
        try {
            this.propertyReader = new PropertyReader(planYourExchangeApplication);
        } catch (IOException e) {
            Log.e(TAG, planYourExchangeApplication.getResources().getString(R.string.app_init_error), e);
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        // -- Initialize Google Analytics
        this.googleAnalytics = GoogleAnalytics.getInstance(planYourExchangeApplication);
        googleAnalytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECONDS);

        this.tracker = googleAnalytics.newTracker(propertyReader.getProperty("AnalyticsId"));
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        // -- Initialize Rest Service Api
        ServerService serverService = new ServerService(propertyReader.getProperty("service.url"),
                propertyReader.getProperty("service.userName"),
                propertyReader.getProperty("service.password"));

        this.serverApi = serverService.serverApi;
    }

    @Provides
    @Singleton
    PropertyReader providePropertyReader() {
        return propertyReader;
    }

    @Provides
    @Singleton
    ServerApi provideServerApi() {
        return serverApi;
    }

    @Provides
    @Singleton
    Tracker provideTracker() {
        return tracker;
    }
}
