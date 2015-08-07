package com.planyourexchange.fragments.schoolcourse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;
import com.planyourexchange.adapters.ScreenSlidePagerAdapter;
import com.planyourexchange.fragments.costofliving.CostOfLivingFragment;
import com.planyourexchange.interfaces.FragmentName;

/**
 * Created by thiago on 06/08/15.
 */
public class SchoolCourseBaseFragment extends Fragment  {
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
        getActivity().setTitle(R.string.countries_title);

        final FragmentManager fragmentManager = getChildFragmentManager();
        // -- Changing activity title when added to backstack
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    FragmentName fragment = (FragmentName) fragmentManager.findFragmentById(R.id.fragment_container);
                    getActivity().setTitle(fragment.getName());
                }
        });

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}
