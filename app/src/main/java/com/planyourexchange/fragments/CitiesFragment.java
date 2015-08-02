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
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class CitiesFragment extends Fragment implements ModelView<City> {

    private static List<City> CITIES_CACHE;

    @Override
    public void setCachedData(List<City> cities) {
        CITIES_CACHE = cities;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countries_fragment,container,false);

        Context context = container.getContext();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.countries_linear_layout);

        // -- Dispatch task to load resources if not cached
        if(CITIES_CACHE==null) {
            Integer countryId = savedInstanceState.getInt(CountriesFragment.COUNTRY_ID);
            new RestLoaderTask<City>(context, viewGroup, this).execute(countryId);
        } else {
            drawList(CITIES_CACHE,context,viewGroup);
        }

        return view;

    }

    @Override
    public void drawList(List<City> cities,Context context, ViewGroup viewGroup) {
        for (City city : cities) {
            TextView textView = new TextView(context);
            textView.setText(city.getName());
            textView.setTextColor(Color.BLACK);
            viewGroup.addView(textView);

            ImageView imageView = new ImageView(context);
            ImageLoader.getInstance().displayImage(city.getIcon(), imageView);
            /*
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            */
            viewGroup.addView(imageView);
        }
    }

    @Override
    public List<City> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCities(modelId);
    }
}
