package com.planyourexchange.fragments.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planyourexchange.R;
import com.planyourexchange.interfaces.FragmentName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
public abstract class AbstractBaseFragment<Key extends Serializable, Model, ModelView extends View> extends GenericFragment implements FragmentName, Callback<Model> {

    protected static final String CACHE_ID = "cacheId";
    protected static final String KEY_ID = "keyId";

    // -- Base properties
    private final int inflateLayout;
    private final int drawLayout;

    // -- Need to be called by overriding class
    protected AbstractBaseFragment(final int titleName, final int inflateLayout, int drawLayout) {
        super(titleName);
        this.inflateLayout = inflateLayout;
        this.drawLayout = drawLayout;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(this.inflateLayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        if(bundle != null && bundle.containsKey(KEY_ID)) {
            updateView(bundle);
        }

        super.onViewCreated(view,savedInstanceState);
    }

    public void updateView(Bundle bundle) {
        Key key = (Key) bundle.getSerializable(KEY_ID);
        Map <Key, Model> CACHE = getCache();

        // -- If the key is in cache
        if(CACHE.containsKey(key)) {
            // -- In case the view is not visible anymore
            View view = getView();
            if(view!=null) {
                ModelView modelView = (ModelView) getView().findViewById(this.drawLayout);
                drawModel(CACHE.get(key), modelView);
            }
        } else {
            // -- Dispatch task to load resources if not cached
            onTaskStarted();
            callService(key);
        }
    }

    // -- Call rest Service
    protected abstract void callService(Key key);
    // -- Draw objects
    protected abstract void drawModel(Model model, ModelView modelView);

    @Override
    public void success(Model model, Response response) {

        getCache().put((Key) getArguments().get(KEY_ID), model);

        View view = getView();
        // -- In case the view is not visible anymore
        if(view!=null) {
            ModelView modelView = (ModelView) view.findViewById(this.drawLayout);
            drawModel(model, modelView);
        }

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

    private Map <Key, Model> getCache() {
        Map <Key, Model> CACHE = (Map) getArguments().get(CACHE_ID);
        if(CACHE==null) {
            // -- Cache of information
            CACHE = new HashMap<Key, Model>();
            getArguments().putSerializable(CACHE_ID, (Serializable)CACHE);
        }
        return CACHE;
    }
}
