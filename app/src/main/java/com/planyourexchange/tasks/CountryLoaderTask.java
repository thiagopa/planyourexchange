package com.planyourexchange.tasks;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.CountriesFragment;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.service.ServerService;
import com.planyourexchange.views.ModelView;

import java.util.List;

/**
 * Created by thiago on 31/07/15.
 */
public class CountryLoaderTask extends AsyncTask<Void, Integer, List<Country>> {

    private Context context;
    private ViewGroup viewGroup;
    private ModelView<Country> modelView;

    public CountryLoaderTask(Context context, ViewGroup viewGroup, ModelView<Country> modelView) {
        this.context = context;
        this.viewGroup = viewGroup;
        this.modelView = modelView;
    }

    @Override
    protected List<Country> doInBackground(Void... params) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCountries();
    }

    @Override
    protected void onPostExecute(List<Country> countries) {
        modelView.setCachedData(countries);
        modelView.drawList(countries,context,viewGroup);
    }
}
