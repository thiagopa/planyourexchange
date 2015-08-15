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

package com.planyourexchange.di;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.planyourexchange.fragments.costofliving.CostOfLivingFragment;
import com.planyourexchange.fragments.schoolcourse.CitiesFragment;
import com.planyourexchange.fragments.schoolcourse.CountriesFragment;
import com.planyourexchange.fragments.schoolcourse.SchoolCourseBaseFragment;
import com.planyourexchange.interfaces.OnChangeListener;
import com.planyourexchange.rest.api.ServerApi;
import com.planyourexchange.utils.PropertyReader;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
@Module
public class FragmentInjectorModule {

    private final CostOfLivingFragment costOfLivingFragment = new CostOfLivingFragment();

    public FragmentInjectorModule() {
        addEmptyBundle(costOfLivingFragment);
    }

    private void addEmptyBundle(Fragment fragment) {
        fragment.setArguments(new Bundle());
    }

    @Provides
    @Named("CostOfLiving")
    OnChangeListener provideCostOfLivingOnChangeListener() {
        return costOfLivingFragment;
    }

    @Provides
    Fragment[] providePageableFragments() {
        return new Fragment[] {
                new SchoolCourseBaseFragment(),
                costOfLivingFragment
        };
    }
}

