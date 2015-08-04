package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.rest.model.School;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class SchoolsFragment extends BaseFragment<Integer,School> {

    public SchoolsFragment() {
        super("Schools",R.layout.schools_fragment, R.id.schools_linear_layout, new SchoolCourseValueFragment());
    }

    @Override
    public List<School> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listSchools(modelId);
    }

    @Override
    protected Serializable createNextKey(School model) {
        SchoolCourseValueKey key = new SchoolCourseValueKey();
        key.setCityId((Integer) getArguments().getSerializable(KEY_ID));
        key.setSchoolId(model.getId());
        return key;
    }
}
