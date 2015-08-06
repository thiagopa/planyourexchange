package com.planyourexchange.fragments.schoolcourse;

import android.os.Bundle;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.BaseFragment;
import com.planyourexchange.rest.model.Country;

import java.util.List;

/**
 * Created by thiago on 31/07/15.
 */
public class CountriesFragment extends BaseFragment<String,Country> {


    public CountriesFragment() {
        super(R.string.countries_title,R.layout.countries_fragment, R.id.countries_list_view, new CitiesFragment());
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