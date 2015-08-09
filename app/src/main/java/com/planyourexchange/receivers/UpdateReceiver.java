package com.planyourexchange.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.planyourexchange.R;
import com.planyourexchange.activities.MainActivity;
import com.planyourexchange.app.PlanYourExchangeApplication;

/**
 * Created by thiago on 08/08/15.
 */
public class UpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // -- Display a simple hover message in case internet is no more :'(
        if (!PlanYourExchangeApplication.isInternetAvailable(context)) {
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }
}
