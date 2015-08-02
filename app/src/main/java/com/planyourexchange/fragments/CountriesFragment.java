package com.planyourexchange.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

/**
 * Created by thiago on 31/07/15.
 */
public class CountriesFragment extends Fragment implements ModelView<Country> {

    public static final String COUNTRY_ID = "countryId";
    private static List<Country> COUNTRY_CACHE;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countries_fragment,container,false);

        Context context = container.getContext();
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.countries_linear_layout);

        // -- Dispatch task to load resources if not cached
        if(COUNTRY_CACHE==null) {
            new RestLoaderTask<Country>(context, viewGroup, this).execute();
        } else {
            drawList(COUNTRY_CACHE,context,viewGroup);
        }

        return view;
    }

    @Override
    public void setCachedData(List<Country> countries) {
        COUNTRY_CACHE = countries;
    }

    @Override
    public void drawList(List<Country> countries,Context context, ViewGroup viewGroup) {
        for (final Country country : countries) {
            TextView textView = new TextView(context);
            textView.setText(country.getName());
            textView.setTextColor(Color.BLACK);
            viewGroup.addView(textView);

            ImageView imageView = new ImageView(context);
            ImageLoader.getInstance().displayImage(country.getIcon(), imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // -- Fragments
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // -- Initializing first fragment
                    CitiesFragment citiesFragment = new CitiesFragment();
                    // -- Passing the country Id
                    Bundle bundle = new Bundle();
                    bundle.putInt(COUNTRY_ID,country.getId());
                    citiesFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.fragment_container, citiesFragment);
                    // -- Used for back button navigation
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                }
            });

            viewGroup.addView(imageView);
        }
    }

    @Override
    public List<Country> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCountries();
    }
}
