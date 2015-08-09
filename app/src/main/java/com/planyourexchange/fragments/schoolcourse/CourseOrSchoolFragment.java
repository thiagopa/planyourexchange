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
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.utils.Constants;

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
public class CourseOrSchoolFragment extends Fragment implements OnClickListener, FragmentName {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_or_school_fragment, container, false);

        // -- Instructing buttons to performe action logic in this fragment
        Button byCourse = (Button) view.findViewById(R.id.button_by_course);
        Button bySchool = (Button) view.findViewById(R.id.button_by_school);

        byCourse.setOnClickListener(this);
        bySchool.setOnClickListener(this);

        return view;
    }

    // -- Avoiding some weird bugs with Dagger 2 DI directly into fragments
    protected Tracker tracker;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
        this.tracker = mainActivity.getTracker();
    }

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        tracker.setScreenName(getName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;

        // -- I could have used a nested if expression, but...
        switch (v.getId()) {
            // -- Load Courses List for given City
            case R.id.button_by_course:
                fragment = new CoursesFragment();
                break;
            // -- Load Schools List for given City
            case R.id.button_by_school:
                fragment = new SchoolsFragment();
                break;

        }

        // -- Analytics click event for model
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(Constants.CATEGORY_NAVIGATION)
                .setAction(Constants.ACTION_CLICK_ON_CHOICE)
                .setLabel( ((TextView)v).getText().toString() )
                .build());

        fragment.setArguments(new Bundle(getArguments()));

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public String getName() {
        return getResources().getString(R.string.course_or_school_title);
    }
}
