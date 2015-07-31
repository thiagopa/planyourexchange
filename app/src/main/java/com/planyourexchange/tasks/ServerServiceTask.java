package com.planyourexchange.tasks;

import android.os.AsyncTask;

import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.views.ViewAbstraction;

/**
 * Created by thiago on 31/07/15.
 */
public class ServerServiceTask extends AsyncTask<String,Integer,ServerApi> {

    private ViewAbstraction viewAbstraction;

    public ServerServiceTask(ViewAbstraction viewAbstraction) {
        this.viewAbstraction = viewAbstraction;
    }

    @Override
    protected ServerApi doInBackground(String... params) {
        ServerService service = new ServerService(params[0],params[1],params[2]);
        return service.getServerApi();
    }

    @Override
    protected void onPostExecute(ServerApi serverApi) {
        CountryLoaderTask countryLoaderTask = new CountryLoaderTask(serverApi,viewAbstraction);
        countryLoaderTask.execute();
    }
}
