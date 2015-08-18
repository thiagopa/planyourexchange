package com.planyourexchange.pageflow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.planyourexchange.interfaces.SelectionListener;

import java.lang.ref.WeakReference;
import java.util.List;

import static com.planyourexchange.utils.Constants.KEY_ID;

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
public class PageFlowPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "PageFlowPagerAdapter";

    // -- Fragment list class to be instantiated at fixed positions
    private final List<Class> fragmentList;
    // -- Fragment bundles to initialize new fragments
    private final SparseArray<Bundle> bundleSparse;
    // -- Fragment list of active fragments that need to be updated with new data
    private final SparseArray<WeakReference<SelectionListener>> selectionListenerSparse;


    public PageFlowPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        fragmentList = PageFlow.newFragmentList();
        int size = fragmentList.size();
        bundleSparse = new SparseArray<>(size);
        selectionListenerSparse = new SparseArray<>(size);
        // -- Hard Coding English Language for now and maybe ever!!!
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ID,"English");
        addBundleToFragment(PageFlow.COUNTRIES.getPosition(),bundle);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentList.get(position).newInstance();
            Bundle mapBundle = bundleSparse.get(position);
            if(mapBundle!=null) {
                fragment.setArguments(mapBundle);
            } else {
                fragment.setArguments(new Bundle());
            }
        } catch (Exception e) {
            Log.e(TAG,e.getMessage());
        }

        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SelectionListener fragment = (SelectionListener) super.instantiateItem(container, position);
        selectionListenerSparse.put(position,new WeakReference<SelectionListener>(fragment));
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addBundleToFragment(int position, Bundle bundle) {
        Bundle mapBundle = bundleSparse.get(position);
        if(mapBundle!=null) {
            mapBundle.putAll(bundle);
        } else {
            bundleSparse.put(position, bundle);
        }
    }

    public void updateTargetView(int position, Bundle bundle) {
        selectionListenerSparse.get(position).get().updateView(bundle);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        selectionListenerSparse.remove(position);
    }

    public void removeKeyFromFragment(int position) {
        bundleSparse.get(position).remove(KEY_ID);
        selectionListenerSparse.get(position).get().clearView();
    }
}
