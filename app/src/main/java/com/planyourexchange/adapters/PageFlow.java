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

import com.planyourexchange.airfare.AirFareFragment;
import com.planyourexchange.fragments.healthinsurance.HealthInsurancesFragment;
import com.planyourexchange.fragments.schoolcourse.CitiesFragment;
import com.planyourexchange.fragments.schoolcourse.CountriesFragment;
import com.planyourexchange.fragments.schoolcourse.CoursesFragment;
import com.planyourexchange.fragments.schoolcourse.SchoolCourseValueFragment;
import com.planyourexchange.result.ResultFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public enum PageFlow {

    COUNTRIES(0,new CountriesFragment()),
    CITIES(1, new CitiesFragment()),
    COURSES(2, new CoursesFragment()),
    SCHOOLS(3, new SchoolCourseValueFragment()),
    HEALTH_INSURANCE(4, new HealthInsurancesFragment()),
    AIR_FARE(5, new AirFareFragment()),
    RESULT(6, new ResultFragment());

    final int position;
    final Fragment screen;

    PageFlow(int position,Fragment screen) {
        this.position = position;
        this.screen = screen;
    }

    public static List<Fragment> fragmentList() {
        List<Fragment> list = new ArrayList<Fragment>(values().length);

        for(PageFlow pageFlow : values()) {
            list.add(pageFlow.position,pageFlow.screen);
        }

        return list;
    }

}
