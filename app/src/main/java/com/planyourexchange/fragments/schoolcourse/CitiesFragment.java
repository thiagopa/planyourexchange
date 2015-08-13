package com.planyourexchange.fragments.schoolcourse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.interfaces.OnChangeListener;
import com.planyourexchange.rest.model.City;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
public class CitiesFragment extends ListViewFragment<Integer,List<City>,ListView> {

    @Inject
    @Named("CostOfLiving")
    OnChangeListener onChangeListener;

    public CitiesFragment() {
        super(R.string.cities_title,R.layout.cities_fragment, R.id.cities_list_view, new CourseOrSchoolFragment());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlanYourExchangeApplication.getPlanYourExchangeComponent(getActivity()).inject(this);
    }

    @Override
    public void callService(Integer modelId) {
        serverApi.listCities(modelId, this);
    }

    @Override
    protected void notifyListener(Bundle bundle) {
        onChangeListener.updateView(bundle);
    }
}
