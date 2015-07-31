package com.planyourexchange;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.TaskStackBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import com.android.vending.billing.IInAppBillingService;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.tasks.CountryLoaderTask;
import com.planyourexchange.tasks.ServerServiceTask;
import com.planyourexchange.utils.PropertyReader;
import com.planyourexchange.views.ViewAbstraction;

import android.os.AsyncTask;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewAbstraction {

    public static final int DISPATCH_PERIOD_IN_SECONDS = 1800;
    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    private AdView adView;
    private PropertyReader propertyReader;
    private ServerApi serverApi;

    ScrollView scroolView;
    LinearLayout linearLayout;

    IInAppBillingService mService;

    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        propertyReader = new PropertyReader(this);

        // -- Analytics init
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(DISPATCH_PERIOD_IN_SECONDS);

        tracker = analytics.newTracker(propertyReader.getProperty("AnalyticsId"));
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);

        // Create global configuration and initialize ImageLoader with this config
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        // Create and load the AdView.
        adView = new AdView(this);
        adView.setAdUnitId(propertyReader.getProperty("AdUnitId"));
        adView.setAdSize(AdSize.SMART_BANNER);

        // Create a RelativeLayout as the main layout and add the gameView.
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.setBackgroundColor(Color.WHITE);

        // Add adView to the bottom of the screen.
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mainLayout.addView(adView, adParams);

        scroolView = new ScrollView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scroolView.addView(linearLayout);

        mainLayout.addView(scroolView);

        // Set the RelativeLayout as the main layout.
        setContentView(mainLayout);

        showBanner();

        // Create In-app purchases
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);

        ServerServiceTask serverServiceTask = new ServerServiceTask(this);
        serverServiceTask.execute(propertyReader.getProperty("service.url"),
                propertyReader.getProperty("service.userName"),
                propertyReader.getProperty("service.password"));
    }

    private void showBanner() {
        adView.setVisibility(View.VISIBLE);
        adView.loadAd(new AdRequest.Builder()
                .addTestDevice(propertyReader.getProperty("TestDeviceId")).build());
    }

    private void hideBanner() {
        adView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mService != null) {
            unbindService(mServiceConn);
        }
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }

    @Override
    public ViewGroup getViewLayout() {
        return linearLayout;
    }
}
