package com.planyourexchange.app;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.planyourexchange.R;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.utils.PropertyReader;

import java.io.IOException;

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
public class PlanYourExchangeApplication extends Application {

    private static final String TAG = PlanYourExchangeContext.class.getCanonicalName();
    private static final int DISPATCH_PERIOD_IN_SECONDS = 1800;

    // -- Method used to initialize all components the app needs
    public void onCreate() {
        super.onCreate();

        // -- Initialize properties Reader
        PropertyReader propertyReader = null;
        try {
            propertyReader = new PropertyReader(this);
        } catch (IOException e) {
            Log.e(TAG, getResources().getString(R.string.app_init_error), e);
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        // -- Initialize Google Analytics
        GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(this);
        googleAnalytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECONDS);

        Tracker tracker = googleAnalytics.newTracker(propertyReader.getProperty("AnalyticsId"));
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // -- Initialize Rest Service Api
        ServerService serverService = new ServerService(propertyReader.getProperty("service.url"),
                propertyReader.getProperty("service.userName"),
                propertyReader.getProperty("service.password"));

        ServerApi serverApi = serverService.serverApi;

        new PlanYourExchangeContext(propertyReader, googleAnalytics, tracker, serverApi);
    }

    public static boolean isInternetAvailable(Context context) {
        //Getting the ConnectivityManager.
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Getting NetworkInfo from the Connectivity manager.
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        //If I received an info and isConnectedOrConnecting return true then there is an Internet connection.
        boolean isConnected = netInfo != null && netInfo.isConnectedOrConnecting();
        // -- First verify if we have internet freely available
        return isConnected;
    }
}
