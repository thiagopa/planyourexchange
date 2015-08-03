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
        switch (v.getId()) {
            // -- Load Restservice for City and Course
            case R.id.button_by_course:
                
                break;
            // -- Load Restservice for City and School
            case R.id.button_by_school:
                break;

        }
    }
}
