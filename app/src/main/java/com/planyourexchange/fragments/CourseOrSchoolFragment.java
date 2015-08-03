package com.planyourexchange.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.planyourexchange.R;
import android.view.View.OnClickListener;

import static com.planyourexchange.utils.Constants.CITY_ID;
import static com.planyourexchange.utils.Constants.COUNTRY_ID;

/**
 * Created by thiago on 02/08/15.
 */
public class CourseOrSchoolFragment extends Fragment implements OnClickListener {
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

        fragment.setArguments(new Bundle(getArguments()));

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }
}
