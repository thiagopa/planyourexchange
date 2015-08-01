package com.planyourexchange.app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.tasks.ServerServiceTask;
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
            Log.e(TAG, "Closing App, can't continue without a property file!!!", e);
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        // -- Initialize Google Analytics
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECONDS);

        Tracker tracker = analytics.newTracker(propertyReader.getProperty("AnalyticsId"));
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // -- Inits the rest service api on background and tight context
        new ServerServiceTask(propertyReader,analytics,tracker).execute();
    }
}
