package com.planyourexchange.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.City;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.tasks.RestLoaderTask;
import com.planyourexchange.views.ModelView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.planyourexchange.utils.Constants.CITY_ID;
import static com.planyourexchange.utils.Constants.COUNTRY_ID;

/**
 * Created by thiago on 02/08/15.
 */
public class CoursesFragment extends BaseFragment<Integer,Course> {

    public CoursesFragment() {
        super(R.layout.courses_fragment, R.id.courses_linear_layout, new SchoolCourseValueFragment());
    }

    @Override
    public List<Course> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCourses(modelId);
    }
}
