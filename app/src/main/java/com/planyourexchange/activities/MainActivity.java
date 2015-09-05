package com.planyourexchange.activities;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.interfaces.ProgressDialogControl;
import com.planyourexchange.interfaces.ViewPagerControl;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.pageflow.PageFlowPagerAdapter;
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
public class MainActivity extends AdActivity implements ProgressDialogControl, ViewPagerControl {

    private static final String PAGE_FLOW_POSITION = "pageFlowPosition";
    private static final String PAGE_FLOW_FRAGMENT_KEYS = "pageFlowFragmentKeys";
    private ViewPager viewPager;
    private PageFlowPagerAdapter pagerAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- This should be rendered first
        setContentView(R.layout.activity_main);
        // -- create a new Ad
        newAdView();
        // -- View Pager & Adapter
        viewPager = (ViewPager) findViewById(R.id.main_pager);
        // -- In case we're restoring from a previous saved sate
        SparseArray<Bundle> fragmentKeys = null;
        int position = 0;
        if(savedInstanceState!=null) {
            fragmentKeys = savedInstanceState.getSparseParcelableArray(PAGE_FLOW_FRAGMENT_KEYS);
            position = savedInstanceState.getInt(PAGE_FLOW_POSITION);
        }

        pagerAdapter = new PageFlowPagerAdapter(getSupportFragmentManager(),fragmentKeys);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
    }

    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            int position = viewPager.getCurrentItem();
            pagerAdapter.removeKeyFromFragment(position);
            viewPager.setCurrentItem(position - 1);
        }
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
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // -- save the current screen
        int position = viewPager.getCurrentItem();
        outState.putInt(PAGE_FLOW_POSITION, position - 1);
        // -- save fragment keys
        SparseArray<Bundle> fragmentKeys = pagerAdapter.getBundleSparse();
        outState.putSparseParcelableArray(PAGE_FLOW_FRAGMENT_KEYS, fragmentKeys);

        super.onSaveInstanceState(outState);
    }
}
