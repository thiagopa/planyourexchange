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
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.util.List;

/**
 * Created by thiago on 02/08/15.
 */
public class SchoolCourseValueFragment extends AbstractBaseFragment<SchoolCourseValueKey,SchoolCourseValue> {

    public SchoolCourseValueFragment() {
        super(R.layout.school_course_value_fragment, R.id.school_course_value_linear_layout);
    }

    @Override
    public List<SchoolCourseValue> callService(SchoolCourseValueKey schoolCourseValueKey) {
        return PlanYourExchangeContext.getInstance().serverService.getServerApi().findCourseSchoolValue(schoolCourseValueKey);
    }

    @Override
    public void drawList(List<SchoolCourseValue> schoolCourseValues, Context context, ViewGroup viewGroup) {

    }
}