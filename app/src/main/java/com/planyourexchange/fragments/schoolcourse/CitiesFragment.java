package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.rest.model.City;

import java.util.List;

/**
 * Created by thiago on 01/08/15.
 */
public class CitiesFragment extends ListViewFragment<Integer,City> {

    public CitiesFragment() {
        super(R.string.cities_title,R.layout.cities_fragment, R.id.cities_list_view, new CourseOrSchoolFragment());
    }

    @Override
    public void callService(Integer modelId) {
        PlanYourExchangeContext.getInstance().serverService.getServerApi().listCities(modelId,this);
    }
}
