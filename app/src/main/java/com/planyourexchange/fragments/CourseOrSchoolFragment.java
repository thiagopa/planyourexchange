package com.planyourexchange.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;

/**
 * Created by thiago on 02/08/15.
 */
public class CourseOrSchoolFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_or_school_fragment, container, false);
    }
}
