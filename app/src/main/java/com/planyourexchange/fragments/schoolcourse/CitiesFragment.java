package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.BaseFragment;
import com.planyourexchange.rest.model.City;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class CitiesFragment extends BaseFragment<Integer,City> {

    public CitiesFragment() {
        super(R.string.cities_title,R.layout.cities_fragment, R.id.cities_list_view, new CourseOrSchoolFragment());
    }

    @Override
    public List<City> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCities(modelId);
    }
}
