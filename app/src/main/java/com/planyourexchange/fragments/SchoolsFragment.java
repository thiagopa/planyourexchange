package com.planyourexchange.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.School;

import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class SchoolsFragment extends BaseFragment<Integer,School> {

    public SchoolsFragment() {
        super(R.layout.schools_fragment, R.id.schools_linear_layout, new SchoolCourseValueFragment());
    }

    @Override
    public List<School> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listSchools(modelId);
    }
}
