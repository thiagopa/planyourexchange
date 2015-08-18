package com.planyourexchange.activities;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
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
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.pageflow.PageFlowPagerAdapter;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.interfaces.ProgressDialogControl;
import com.planyourexchange.interfaces.ViewPagerControl;
import com.planyourexchange.utils.PropertyReader;

import javax.inject.Inject;

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
public class MainActivity extends AppCompatActivity implements ProgressDialogControl, ViewPagerControl {

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
    @Inject
    PropertyReader propertyReader;

    private ViewPager viewPager;
    private PageFlowPagerAdapter pagerAdapter;

    private ProgressDialog progressDialog;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- Inject dependecies first
        PlanYourExchangeApplication.getPlanYourExchangeComponent(this).inject(this);
        // -- This should be rendered first
        setContentView(R.layout.activity_main);

        // Create In-app purchases
            /*
            -- TODO Implement this latter
            Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            serviceIntent.setPackage("com.android.vending");
            bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
            */
        // -- create a new Ad
        newAdView();
        // -- View Pager & Adapter
        viewPager = (ViewPager) findViewById(R.id.main_pager);

        pagerAdapter = new PageFlowPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private void newAdView() {
        // -- Relative Layout manipulation
        RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        if(adView!=null) {
            mainLayout.removeView(adView);
        }
        // -- Create adRequest
        adView = new AdView(this);
        adView.setAdUnitId(propertyReader.getProperty("AdUnitId"));
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        adParams.addRule(RelativeLayout.CENTER_VERTICAL);
        mainLayout.addView(adView, adParams);
        // -- Request a new Ad to be loaded

        // -- TODO should be replaced in production
        adView.loadAd(new AdRequest.Builder()
                .addTestDevice(propertyReader.getProperty("TestDeviceId")).build());
    }


    /* TODO -- Leave this to hide banners when donated
    private void hideBanner() {
        adView.setVisibility(View.GONE);
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }


    /*
    @Override
    public void onBackPressed() {
        SparseArray<FragmentManager> managers = new SparseArray<>();
        traverseManagegetItemrs(getSupportFragmentManager(), managers, 0);
        if (managers.size() > 0) {
            managers.valueAt(managers.size() - 1).popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    private void traverseManagers(FragmentManager manager, SparseArray<FragmentManager> managers, int intent) {
        if (manager.getBackStackEntryCount() > 0) {
            managers.put(intent, manager);
        }
        if (manager.getFragments() == null) {
            return;
        }
        for (Fragment fragment : manager.getFragments()) {
            if (fragment != null) traverseManagers(fragment.getChildFragmentManager(), managers, intent + 1);
        }
    }
    */

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        newAdView();
    }

    @Override
    public void show() {
        progressDialog = ProgressDialog.show(this,
                getResources().getString(R.string.loading_title),
                getResources().getString(R.string.loading_dialog));
    }

    @Override
    public void dismiss() {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void nextScreen(PageFlow pageFlow, Bundle bundle) {

        int position = pageFlow.getPosition();
        // -- Store information for future fragments
        pagerAdapter.addBundleToFragment(position,bundle);
        // -- Update View with new data
        pagerAdapter.updateTargetView(position,bundle);
        // -- Swipe view
        viewPager.setCurrentItem(position);
    }

    @Override
    public void previousScreen() {
        int position = viewPager.getCurrentItem();
        pagerAdapter.removeKeyFromFragment(position);
        onBackPressed();
    }

    //    onDe
//
//    @Override
//    public void onDetach() {
//        // -- In case the task never finishes...
//        if(progressDialog!=null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
//        super.onDetach();
//    }
}
