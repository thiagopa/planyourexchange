package com.planyourexchange.fragments.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.fragments.schoolcourse.CitiesFragment;
import com.planyourexchange.rest.model.BaseModel;
import com.planyourexchange.utils.Constants;
import com.planyourexchange.utils.InternationalNames;

import java.io.Serializable;
import java.util.Collections;
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
// -- Base model for handling information between fragments that share enormous similarities
public abstract class ListViewFragment<Key extends Serializable, Model extends BaseModel> extends AbstractBaseFragment<Key, List<Model>, ListView> {

    private final Fragment nextScreen;
    private final int headerName;

    // -- Need to be called by overriding class
    protected ListViewFragment(final int titleName, final int headerName, final Fragment nextScreen) {
        super(titleName,R.layout.base_list_fragment,R.id.base_list_view);
        this.nextScreen = nextScreen;
        this.headerName = headerName;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // -- Sets the text header
        ((TextView)view.findViewById(R.id.base_list_header)).setText(headerName);
        super.onViewCreated(view,savedInstanceState);
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
                View rowView = inflater.inflate(R.layout.model_list,null,true);

                Model model = modelList.get(position);

                ImageView imageView = (ImageView) rowView.findViewById(R.id.model_list_icon);
                TextView textView = (TextView) rowView.findViewById(R.id.model_list_name);

                ImageLoader.getInstance().displayImage(model.getIcon(), imageView);
                textView.setText(InternationalNames.getInternationalName(getContext(),model.getName()));

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
                nextScreen.setArguments(bundle);

                // -- Notify any Listener attached
                notifyListener(bundle);

                // -- Analytics click event for model
                tracker.send(new HitBuilders.EventBuilder()
                        .setCategory(Constants.CATEGORY_NAVIGATION)
                        .setAction(Constants.ACTION_CLICK_ON_MODEL)
                        .setLabel(model.getName())
                        .build());

                // -- Creating transaction and adding to back stack navigation
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, nextScreen)
                        .addToBackStack(null)
                        .commit();
            }
        });
        // -- Notify that new data has arrived
        listView.deferNotifyDataSetChanged();
    }

    // -- Defaults to NO ACTION
    protected void notifyListener(Bundle bundle) {
    }

    // -- Default is the model id as key
    protected Serializable createNextKey(Model model) {
        return model.getId();
    }
}
