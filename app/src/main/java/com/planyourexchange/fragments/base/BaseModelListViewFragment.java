/*
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

package com.planyourexchange.fragments.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.interfaces.GenericModel;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.utils.InternationalNames;

import java.io.Serializable;

/**
 * @author Thiago Pagonha
 * @version 18/08/15.
 */
public abstract class BaseModelListViewFragment<Key extends Serializable, Model extends Comparable & GenericModel> extends ListViewFragment<Key,Model> {

    protected BaseModelListViewFragment(int titleName, int headerName,PageFlow nextScreen) {
        super(titleName, headerName, R.layout.model_list,nextScreen);
    }

    @Override
    protected Serializable createNextKey(Model model) {
        return model.getId();
    }

    @Override
    protected void renderSingleModel(Model model, View rowView) {
        ImageView imageView = (ImageView) rowView.findViewById(R.id.model_list_icon);
        TextView textView = (TextView) rowView.findViewById(R.id.model_list_name);

        ImageLoader.getInstance().displayImage(model.getIcon(), imageView);
        textView.setText(InternationalNames.getInternationalName(getActivity(), model.getName()));
    }
}
