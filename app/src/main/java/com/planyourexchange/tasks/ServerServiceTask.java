package com.planyourexchange.tasks;

import android.os.AsyncTask;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.utils.PropertyReader;

/**
 * Created by thiago on 31/07/15.
 */
public class ServerServiceTask extends AsyncTask<Void, Void, ServerApi> {

    private PropertyReader propertyReader;
    private GoogleAnalytics googleAnalytics;
    private Tracker tracker;

    public ServerServiceTask(PropertyReader propertyReader, GoogleAnalytics googleAnalytics, Tracker tracker) {
        this.propertyReader = propertyReader;
        this.googleAnalytics = googleAnalytics;
        this.tracker = tracker;
    }


    @Override
    protected ServerApi doInBackground(Void... params) {
        ServerService service = new ServerService(propertyReader.getProperty("service.url"),
                propertyReader.getProperty("service.userName"),
                propertyReader.getProperty("service.password"));
        return service.getServerApi();
    }

    @Override
    protected void onPostExecute(ServerApi serverApi) {
        new PlanYourExchangeContext(propertyReader, googleAnalytics, tracker, serverApi);
    }
}
