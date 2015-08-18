package com.planyourexchange.fragments.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.interfaces.ProgressDialogControl;
import com.planyourexchange.interfaces.ViewPagerControl;
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
    private ProgressDialogControl progressDialogControl;
    private ViewPagerControl viewPagerControl;

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
    public int getResourceId() {
        return titleName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlanYourExchangeApplication.getPlanYourExchangeComponent(getActivity()).inject(this);
    }


    protected void onTaskStarted() {
        progressDialogControl.show();
    }

    protected void onTaskFinished() {
        progressDialogControl.dismiss();
    }

    protected void nextScreen(PageFlow pageFlow, Bundle bundle) {
        viewPagerControl.nextScreen(pageFlow,bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        progressDialogControl = (ProgressDialogControl) activity;
        viewPagerControl = (ViewPagerControl) activity;
        super.onAttach(activity);
    }
}
