package com.planyourexchange.app;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.planyourexchange.R;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.utils.PropertyReader;

import java.io.IOException;

/**
 * Created by thiago on 31/07/15.
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

        new PlanYourExchangeContext(propertyReader, googleAnalytics, tracker, serverService);
    }
}
