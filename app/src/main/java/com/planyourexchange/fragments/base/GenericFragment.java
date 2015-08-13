package com.planyourexchange.fragments.base;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.rest.api.ServerApi;

import javax.inject.Inject;

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
public abstract class GenericFragment extends Fragment implements FragmentName {

    @Inject
    protected Tracker tracker;
    @Inject
    protected ServerApi serverApi;

    private final int titleName;

    private ProgressDialog progressDialog;


    public GenericFragment(int titleName) {
        this.titleName = titleName;
    }

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        tracker.setScreenName(getName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public String getName() {
        return getResources().getString(titleName);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlanYourExchangeApplication.getPlanYourExchangeComponent(getActivity()).inject(this);
    }


    protected void onTaskStarted() {
        progressDialog = ProgressDialog.show(getActivity(),
                getResources().getString(R.string.loading_title),
                getResources().getString(R.string.loading_dialog));
    }

    protected void onTaskFinished() {
        if(progressDialog!=null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDetach() {
        // -- In case the task never finishes...
        if(progressDialog!=null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDetach();
    }
}
