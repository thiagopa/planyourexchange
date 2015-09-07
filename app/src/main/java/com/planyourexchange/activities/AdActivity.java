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

package com.planyourexchange.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.pageflow.PageFlowContext;
import com.planyourexchange.utils.PropertyReader;

import javax.inject.Inject;

/**
 * @author Thiago Pagonha
 * @version 04/09/15.
 */
public class AdActivity extends AppCompatActivity {

    private static final String TAG = "AdActivity";

    @Inject
    PropertyReader propertyReader;
    @Inject
    PageFlowContext pageFlowContext;

    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- Inject dependecies first
        PlanYourExchangeApplication.getPlanYourExchangeComponent(this).inject(this);
    }

    protected void newAdView() {
        try {
            // -- Relative Layout manipulation
            RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

            if (adView != null) {
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
        }catch (Exception e) {
            Log.e(TAG, "Couldn't instantiate ad because", e);
        }
    }

    // TODO -- Leave this to hide banners when donated
    protected void hideBanner() {
        adView.setVisibility(View.GONE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        newAdView();
    }
}
