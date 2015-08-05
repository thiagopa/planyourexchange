package com.planyourexchange.fragments.schoolcourse;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.utils.Constants;

/**
 * Created by thiago on 02/08/15.
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

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        Tracker tracker = PlanYourExchangeContext.getInstance().tracker;
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
        Tracker tracker = PlanYourExchangeContext.getInstance().tracker;
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
        return "Course Or School";
    }
}
