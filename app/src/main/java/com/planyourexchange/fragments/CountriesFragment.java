package com.planyourexchange.fragments;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;

import java.util.List;

import static com.planyourexchange.utils.Constants.COUNTRY_ID;

/**
 * Created by thiago on 31/07/15.
 */
public class CountriesFragment extends BaseFragment<String,Country> {


    public CountriesFragment() {
        super(R.layout.countries_fragment, R.id.countries_linear_layout, new CitiesFragment());
        // -- Hard Coding English Language for now
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID,"English");
        setArguments(bundle);
    }

    // -- List all countries (only english for now)
    @Override
    public List<Country> callService(String language) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCountries();
    }
}
