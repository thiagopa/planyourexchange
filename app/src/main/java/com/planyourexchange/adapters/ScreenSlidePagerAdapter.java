package com.planyourexchange.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thiago on 06/08/15.
 */
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager, Fragment...fragments) {
        super(fragmentManager);
        fragmentList = new ArrayList<Fragment>(fragments.length);
        for (Fragment fragment : fragments) {
            fragmentList.add(fragment);
        }
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
