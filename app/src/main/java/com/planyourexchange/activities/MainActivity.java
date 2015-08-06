package com.planyourexchange.activities;

import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.planyourexchange.R;
import com.planyourexchange.adapters.ScreenSlidePagerAdapter;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.costofliving.CostOfLivingFragment;
import com.planyourexchange.fragments.schoolcourse.SchoolCourseBaseFragment;


public class MainActivity extends AppCompatActivity {

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
    // -- View Pager
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- This should come first
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }
        // -- Relative Layout manipulation
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        // Create In-app purchases
            /*
            -- TODO Implement this latter
            Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            serviceIntent.setPackage("com.android.vending");
            bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
            */

        // -- Create adRequest
        AdView adView = new AdView(this);
        adView.setAdUnitId(PlanYourExchangeContext.getInstance().propertyReader.getProperty("AdUnitId"));
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mainLayout.addView(adView, adParams);

        // -- TODO should be replaced in production
        adView.loadAd(new AdRequest.Builder()
                .addTestDevice(PlanYourExchangeContext.getInstance().propertyReader.getProperty("TestDeviceId")).build());
            /*
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            */
        // -- View Pager Adapter
        viewPager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),
                new SchoolCourseBaseFragment(),
                new CostOfLivingFragment());

        viewPager.setAdapter(pagerAdapter);


        // -- Fragment Manager
        //FragmentManager fragmentManager = getFragmentManager();

        // -- Changing title according to fragment
            /*fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    FragmentName fragment = (FragmentName) getFragmentManager().findFragmentById(R.id.fragment_container);
                    setTitle(fragment.getName());
                }
            });*/


    }

    /* TODO -- Leave this to hide banners when donated
    private void hideBanner() {
        adView.setVisibility(View.GONE);
    }
    */

    @Override
    public void onBackPressed() {
        /*if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
        */
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
}
