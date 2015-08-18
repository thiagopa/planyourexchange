package com.planyourexchange.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "ScreenSlidePagerAdapter";

    // -- Fragment list
    private final List<Class<? extends Fragment>> fragmentList;
    // -- Fragment arguments
    private final List<Bundle> bundleList;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentList = PageFlow.newFragmentList();
        bundleList = new ArrayList<Bundle>(fragmentList.size());
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        Fragment fragment = null;

        try {
            fragment = fragmentList.get(position).newInstance();
            fragment.setArguments(bundleList.get(position));
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addBundleToFragment(int position, Bundle bundle) {
        Bundle listedBundle = bundleList.get(position);
        if(bundle!=null) {
            listedBundle.putAll(bundle);
        } else {
          bundleList.set(position,bundle);
        }
    }



}
