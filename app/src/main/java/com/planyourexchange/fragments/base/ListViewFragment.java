package com.planyourexchange.fragments.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.utils.Constants;
import com.planyourexchange.utils.InternationalNames;

import java.io.Serializable;
import java.util.Collections;
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
// -- Base model for handling information between fragments that share enormous similarities
public abstract class ListViewFragment<Key extends Serializable, Model extends Comparable> extends AbstractBaseFragment<Key, List<Model>, ListView> {

    private final PageFlow nextScreen;
    private final int headerName;

    protected ListViewFragment(int titleName, int headerName, int drawLayout ,PageFlow nextScreen) {
        super(titleName, R.layout.base_list_fragment, drawLayout);
        this.nextScreen = nextScreen;
        this.headerName = headerName;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // -- Sets the text header
        ((TextView)view.findViewById(R.id.base_list_header)).setText(headerName);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void drawModel(final List<Model> modelList, ListView listView) {
        // -- Sort Results Alphabetically by default
        Collections.sort(modelList);

        // -- Handle Model rendering
        listView.setAdapter(new ArrayAdapter<Model>(getActivity(),R.layout.model_list,modelList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getLayoutInflater(null);
                View rowView = inflater.inflate(R.layout.model_list, null, true);

                Model model = modelList.get(position);

                renderSingleModel(model, rowView);

                return rowView;
            }
        });
        // -- Handle onClick events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Model model = modelList.get(position);

                Bundle bundle = new Bundle();
                bundle.putSerializable(KEY_ID, createNextKey(model));

                // -- Analytics click event for model
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory(Constants.CATEGORY_NAVIGATION)
                        .setAction(Constants.ACTION_CLICK_ON_MODEL)
                        .setLabel(model.toString())
                        .build());
                // -- Trigger action to change screen
                nextScreen(nextScreen, bundle);
            }
        });
        // -- Notify that new data has arrived
        listView.deferNotifyDataSetChanged();
    }

    /**
     * Create key for next key based on model
     * @param model
     * @return
     */
    protected abstract Serializable createNextKey(Model model);

    /**
     * Render specific model based on its rowView
     * @param model
     * @param rowView
     */
    protected abstract void renderSingleModel(Model model, View rowView);
}
