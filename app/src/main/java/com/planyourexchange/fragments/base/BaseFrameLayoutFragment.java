package com.planyourexchange.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;
import com.planyourexchange.interfaces.FragmentName;

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
public class BaseFrameLayoutFragment extends Fragment  {

    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_framelayout, container, false);
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // -- Initializing first fragment and setting screen title
        getActivity().setTitle(((FragmentName)fragment).getResourceId());

        final FragmentManager fragmentManager = getChildFragmentManager();
        // -- Changing activity title when added to backstack
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    FragmentName fragment = (FragmentName) fragmentManager.findFragmentById(R.id.fragment_container);
                    getActivity().setTitle(fragment.getResourceId());
                }
        });

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        super.onActivityCreated(savedInstanceState);
    }
}
