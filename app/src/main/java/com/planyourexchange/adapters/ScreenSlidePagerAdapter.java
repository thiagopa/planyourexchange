package com.planyourexchange.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<Integer,Bundle> bundleMap;

    public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentList = PageFlow.newFragmentList();
        bundleMap = new HashMap<Integer,Bundle>(fragmentList.size());
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        Fragment fragment = null;

        try {
            fragment = fragmentList.get(position).newInstance();
            Bundle mapBundle = bundleMap.get(position);
            if(mapBundle!=null) {
                fragment.setArguments(mapBundle);
            }
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
        Bundle mapBundle = bundleMap.get(position);
        if(mapBundle!=null) {
            mapBundle.putAll(bundle);
        } else {
            bundleMap.put(position,bundle);
        }
    }



}
