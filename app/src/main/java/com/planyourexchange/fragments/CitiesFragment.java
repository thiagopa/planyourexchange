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
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;
import static com.planyourexchange.utils.Constants.CITY_ID;
import static com.planyourexchange.utils.Constants.COUNTRY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thiago on 01/08/15.
 */
public class CitiesFragment extends Fragment implements ModelView<City> {

    private final static Map<Country, List<City>> COUNTRY_CITIES_CACHE = new HashMap<Country, List<City>>();

    @Override
    public void setCachedData(List<City> cities) {
        COUNTRY_CITIES_CACHE.put(cities.get(0).getCountry(), cities);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cities_fragment, container, false);

        Context context = container.getContext();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.cities_linear_layout);
        Integer countryId = getArguments().getInt(COUNTRY_ID);
        Country lookup = new Country();
        lookup.setId(countryId);

        // -- Dispatch task to load resources if not cached
        if (COUNTRY_CITIES_CACHE.containsKey(lookup)) {
            drawList(COUNTRY_CITIES_CACHE.get(lookup), context, viewGroup);
        } else {
            new RestLoaderTask<City>(context, viewGroup, this).execute(countryId);
        }

        return view;

    }

    @Override
    public void drawList(List<City> cities, Context context, ViewGroup viewGroup) {
        for (final City city : cities) {
            TextView textView = new TextView(context);
            textView.setText(city.getName());
            textView.setTextColor(Color.BLACK);
            viewGroup.addView(textView);

            ImageView imageView = new ImageView(context);
            ImageLoader.getInstance().displayImage(city.getIcon(), imageView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // -- Initializing course or school fragment
                    CourseOrSchoolFragment courseOrSchoolFragment = new CourseOrSchoolFragment();
                    // -- Passing the country Id
                    Bundle bundle = new Bundle();
                    bundle.putInt(CITY_ID, city.getId());
                    courseOrSchoolFragment.setArguments(bundle);
                    // -- Creating transaction and adding to back stack navigation
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, courseOrSchoolFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });

            viewGroup.addView(imageView);
        }
    }

    @Override
    public List<City> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCities(modelId);
    }
}
