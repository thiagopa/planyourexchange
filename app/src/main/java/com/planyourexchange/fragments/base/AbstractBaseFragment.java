package com.planyourexchange.fragments.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.activities.MainActivity;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.interfaces.FragmentName;
import com.planyourexchange.rest.api.ServerApi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
// -- Base model for handling information between fragments that share enormous similarities
public abstract class AbstractBaseFragment<Key extends Serializable, Model> extends ProgressDialogFragment implements FragmentName, Callback<List<Model>> {

    // -- Cache of information
    private final Map<Key, List<Model>> CACHE = new HashMap<Key, List<Model>>();

    protected static final String KEY_ID = "keyId";

    // -- Base properties
    private final int inflateLayout;
    private final int titleName;
    private final int drawLayout;

    // -- Need to be called by overriding class
    protected AbstractBaseFragment(final int titleName, final int inflateLayout, int drawLayout) {
        this.titleName = titleName;
        this.inflateLayout = inflateLayout;
        this.drawLayout = drawLayout;
    }

    // -- Avoiding some weird bugs with Dagger 2 DI directly into fragments
    protected Tracker tracker;
    protected ServerApi serverApi;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MainActivity mainActivity = (MainActivity)activity;
        this.tracker = mainActivity.getTracker();
        this.serverApi = mainActivity.getServerApi();
    }

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        tracker.setScreenName(getName());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.inflateLayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Key key = (Key) getArguments().getSerializable(KEY_ID);
        ListView listView = (ListView) getActivity().findViewById(this.drawLayout);

        // -- Dispatch task to load resources if not cached
        if (CACHE.containsKey(key)) {
            drawList(CACHE.get(key),listView);
        } else {
            onTaskStarted();
            callService(key);
        }
    }

    // -- Call rest Service
    protected abstract void callService(Key key);
    // -- Draw objects
    protected abstract void drawList(List<Model> modelList, ListView listView);

    @Override
    public void success(List<Model> modelList, Response response) {
        ListView listView = (ListView) getActivity().findViewById(this.drawLayout);
        CACHE.put((Key) getArguments().get(KEY_ID), modelList);
        drawList(modelList, listView);
        onTaskFinished();
    }

    @Override
    public void failure(RetrofitError error) {
        onTaskFinished();

        String message = error.getMessage()!=null? error.getMessage(): getResources().getString(R.string.no_data_server);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setTitle(R.string.error)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }

    @Override
    public String getName() {
        return getResources().getString(titleName);
    }
}
