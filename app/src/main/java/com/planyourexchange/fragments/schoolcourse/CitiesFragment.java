package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.fragments.base.BaseModelListViewFragment;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.City;

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
public class CitiesFragment extends BaseModelListViewFragment<Integer,City> {

    public CitiesFragment() {
        super(R.string.cities_title, R.string.choose_city, PageFlow.COURSES);
    }

    @Override
    public void callService(Integer modelId) {
        serverApi.listCities(modelId, this);
    }

    @Override
    protected void saveOption(City city) {
        pageFlowContext.setCity(city);
    }
}
