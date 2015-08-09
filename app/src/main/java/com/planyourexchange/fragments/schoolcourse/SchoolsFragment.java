package com.planyourexchange.fragments.schoolcourse;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeContext;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.rest.model.School;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.io.Serializable;
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
public class SchoolsFragment extends ListViewFragment<Integer,School> {

    public SchoolsFragment() {
        super(R.string.schools_title,R.layout.schools_fragment, R.id.schools_list_view, new SchoolCourseValueFragment());
    }

    @Override
    public void callService(Integer modelId) {
        PlanYourExchangeContext.instance.serverApi.listSchools(modelId, this);
    }

    @Override
    protected Serializable createNextKey(School model) {
        SchoolCourseValueKey key = new SchoolCourseValueKey();
        key.setCityId((Integer) getArguments().getSerializable(KEY_ID));
        key.setSchoolId(model.getId());
        return key;
    }
}
