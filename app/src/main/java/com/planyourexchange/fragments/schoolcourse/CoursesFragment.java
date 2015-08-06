package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.BaseFragment;
import com.planyourexchange.rest.model.Course;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class CoursesFragment extends BaseFragment<Integer,Course> {

    public CoursesFragment() {
        super(R.string.courses_title, R.layout.courses_fragment, R.id.courses_linear_layout, new SchoolCourseValueFragment());
    }

    @Override
    public List<Course> callService(Integer modelId) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().listCourses(modelId);
    }

    @Override
    protected Serializable createNextKey(Course model) {
        SchoolCourseValueKey key = new SchoolCourseValueKey();
        key.setCityId( (Integer) getArguments().getSerializable(KEY_ID));
        key.setCourseId(model.getId());
        return key;
    }
}
