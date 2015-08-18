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

package com.planyourexchange.adapters;

import android.support.v4.app.Fragment;

import com.planyourexchange.fragments.airfare.AirFareFragment;
import com.planyourexchange.fragments.healthinsurance.HealthInsurancesFragment;
import com.planyourexchange.fragments.schoolcourse.CitiesFragment;
import com.planyourexchange.fragments.schoolcourse.CountriesFragment;
import com.planyourexchange.fragments.schoolcourse.CoursesFragment;
import com.planyourexchange.fragments.schoolcourse.SchoolCourseValueFragment;
import com.planyourexchange.fragments.result.ResultFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public enum PageFlow {

    COUNTRIES(0, CountriesFragment.class),
    CITIES(1, CitiesFragment.class),
    COURSES(2, CoursesFragment.class),
    SCHOOLS(3, SchoolCourseValueFragment.class),
    HEALTH_INSURANCE(4, HealthInsurancesFragment.class),
    AIR_FARE(5, AirFareFragment.class),
    RESULT(6, ResultFragment.class);

    private final int position;
    private final Class<? extends Fragment> screen;

    PageFlow(int position,Class<? extends Fragment> screen) {
        this.position = position;
        this.screen = screen;
    }

    public static List<Class<? extends Fragment>> newFragmentList() {
        List<Class<? extends Fragment>> list = new ArrayList<Class<? extends Fragment>>(values().length);

        for(PageFlow pageFlow : values()) {
            list.add(pageFlow.position,pageFlow.screen);
        }

        return list;
    }

    public int getPosition() {
        return position;
    }
}
