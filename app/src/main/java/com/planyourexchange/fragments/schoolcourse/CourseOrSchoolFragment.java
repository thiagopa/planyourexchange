package com.planyourexchange.fragments.schoolcourse;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.activities.MainActivity;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.fragments.base.GenericFragment;
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.utils.Constants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Copyright (C) 2015, Thiago Pagonha,
 * Plan Your Exchange, easy exchange to fit your budget
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
public class CourseOrSchoolFragment extends GenericFragment {

    public CourseOrSchoolFragment() {
        super(R.string.course_or_school_title);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_or_school_fragment, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.button_by_course)
    public void byCourse(View view) {
        onClick(view,new CoursesFragment());
    }

    @OnClick(R.id.button_by_school)
    public void bySchool(View view) {
        onClick(view,new SchoolsFragment());
    }

    private void onClick(View view, Fragment fragment) {

        // -- Analytics click event for model
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(Constants.CATEGORY_NAVIGATION)
                .setAction(Constants.ACTION_CLICK_ON_CHOICE)
                .setLabel( ((TextView)view).getText().toString() )
                .build());

        fragment.setArguments(new Bundle(getArguments()));

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }
}

