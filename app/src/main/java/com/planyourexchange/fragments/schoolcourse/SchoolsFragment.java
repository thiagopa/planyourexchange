package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.rest.model.School;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class SchoolsFragment extends ListViewFragment<Integer,School> {

    public SchoolsFragment() {
        super(R.string.schools_title,R.layout.schools_fragment, R.id.schools_list_view, new SchoolCourseValueFragment());
    }

    @Override
    public void callService(Integer modelId) {
        PlanYourExchangeContext.getInstance().serverService.getServerApi().listSchools(modelId,this);
    }

    @Override
    protected Serializable createNextKey(School model) {
        SchoolCourseValueKey key = new SchoolCourseValueKey();
        key.setCityId((Integer) getArguments().getSerializable(KEY_ID));
        key.setSchoolId(model.getId());
        return key;
    }
}
