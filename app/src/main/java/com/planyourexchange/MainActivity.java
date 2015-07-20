package com.planyourexchange;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    private AdView adView;
    private PropertyReader propertyReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyReader = new PropertyReader(this);

        SurfaceView surfaceView = new SurfaceView(this);

        // Create and load the AdView.
        adView = new AdView(this);
        adView.setAdUnitId(propertyReader.getProperty("AdUnitId"));
        adView.setAdSize(AdSize.SMART_BANNER);

        // Create a RelativeLayout as the main layout and add the gameView.
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.addView(surfaceView);

        // Add adView to the bottom of the screen.
        RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mainLayout.addView(adView, adParams);

        // Set the RelativeLayout as the main layout.
        setContentView(mainLayout);

        showBanner();
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
}
