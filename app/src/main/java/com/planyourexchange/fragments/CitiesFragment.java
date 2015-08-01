package com.planyourexchange.fragments;

import android.app.Fragment;
import android.content.Context;
import android.view.ViewGroup;

import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.views.ModelView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiago on 01/08/15.
 */
public class CitiesFragment extends Fragment implements ModelView<City> {

    private static List<City> CITIES_CACHE;

    @Override
    public void setCachedData(List<City> cities) {
        CITIES_CACHE = cities;
    }

    @Override
    public void drawList(List<City> cities, Context context, ViewGroup viewGroup) {

    }
}
