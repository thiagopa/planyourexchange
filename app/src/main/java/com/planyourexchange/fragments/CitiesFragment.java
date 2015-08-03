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
public class CitiesFragment extends BaseFragment<Integer,City> {

    public CitiesFragment() {
        super(R.layout.cities_fragment, R.id.cities_linear_layout, new CourseOrSchoolFragment());
    }

    @Override
    public List<City> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCities(modelId);
    }
}
