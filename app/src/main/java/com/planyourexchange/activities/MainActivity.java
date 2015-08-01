package com.planyourexchange.activities;

import android.content.Context;
import android.support.v4.app.TaskStackBuilder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.utils.PropertyReader;
import com.planyourexchange.views.ViewAbstraction;

public class MainActivity extends AppCompatActivity implements ViewAbstraction {

    private AdView adView;
    /*
    / -- TODO Implement this latter
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
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- This should come first
        setContentView(R.layout.activity_main);

        // Create and load the AdView.
        adView = new AdView(this);
        adView.setAdUnitId(PlanYourExchangeContext.getInstance().propertyReader.getProperty("AdUnitId"));
        adView.setAdSize(AdSize.SMART_BANNER);

        // -- Relative Layout manipulation
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        // Add adView to the bottom of the screen.
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mainLayout.addView(adView, adParams);

        showBanner();

        // Create In-app purchases
        /*
         -- TODO Implement this latter
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
        */

        // -- Fragments
        /*
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // -- Initializing first fragment
        CountriesFragment countriesFragment = new CountriesFragment();
        fragmentTransaction.add(R.id.mainLayout,countriesFragment);
        fragmentTransaction.commit();

        ServerServiceTask serverServiceTask = new ServerServiceTask(this);
        serverServiceTask.execute(propertyReader.getProperty("service.url"),
                propertyReader.getProperty("service.userName"),
                propertyReader.getProperty("service.password"));
        */
    }

    private void showBanner() {
        adView.setVisibility(View.VISIBLE);
        adView.loadAd(new AdRequest.Builder()
                .addTestDevice(PlanYourExchangeContext.getInstance().propertyReader.getProperty("TestDeviceId")).build());
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
        /*
        TODO Implement this latter
        if (mService != null) {
            unbindService(mServiceConn);
        }
        */
    }

    @Override
    public Context getViewContext() {
        return getApplicationContext();
    }

    @Override
    public ViewGroup getViewLayout() {
        return (ViewGroup) findViewById(R.id.countries_linear_layout);
    }
}
