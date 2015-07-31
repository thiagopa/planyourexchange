package com.planyourexchange.tasks;

import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.views.ViewAbstraction;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by thiago on 31/07/15.
 */
public class CountryLoaderTask extends AsyncTask<Void, Integer, List<Country>> {

    private ServerApi serverApi;
    private ViewAbstraction viewAbstraction;

    public CountryLoaderTask(ServerApi serverApi, ViewAbstraction viewAbstraction) {
        this.serverApi = serverApi;
        this.viewAbstraction = viewAbstraction;
    }

    @Override
    protected List<Country> doInBackground(Void... params) {
        return serverApi.listCountries();
    }

    @Override
    protected void onPostExecute(List<Country> countries) {
        for (Country country : countries) {
            TextView textView = new TextView(viewAbstraction.getViewContext());
            textView.setText(country.getName());
            textView.setTextColor(Color.BLACK);
            viewAbstraction.getViewLayout().addView(textView);

            ImageView imageView = new ImageView(viewAbstraction.getViewContext());
            ImageLoader.getInstance().displayImage(country.getIcon(),imageView);
            viewAbstraction.getViewLayout().addView(imageView);
        }
    }
}
