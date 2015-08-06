package com.planyourexchange.fragments.schoolcourse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;

/**
 * Created by thiago on 06/08/15.
 */
public class SchoolCourseBaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.school_course_base_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // -- Initializing first fragment and setting screen title
        CountriesFragment fragment = new CountriesFragment();
        // setTitle(R.string.countries_title);

        FragmentManager fragmentManager = getChildFragmentManager();

        /*fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    FragmentName fragment = (FragmentName) getFragmentManager().findFragmentById(R.id.fragment_container);
                    setTitle(fragment.getName());
                }
            });*/

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
