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

package com.planyourexchange.app;

import com.planyourexchange.activities.MainActivity;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.fragments.schoolcourse.CourseOrSchoolFragment;

/**
 * Tried Dagger 2 but was disappointed with slower performance and hellish bugs over my injected classes (Fragment lifecycle)
 * Manually injecting dependencies for now
 * @author Thiago Pagonha
 * @version 09/08/15.
 */
public class PlanYourExchangeComponentInjector implements PlanYourExchangeComponent {

    private final PlanYourExchangeModule planYourExchangeModule;

    public PlanYourExchangeComponentInjector(PlanYourExchangeApplication planYourExchangeApplication) {
        this.planYourExchangeModule = new PlanYourExchangeModule(planYourExchangeApplication);
    }

    @Override
    public void inject(MainActivity mainActivity) {
        mainActivity.setPropertyReader(planYourExchangeModule.providePropertyReader());
    }

    @Override
    public void inject(AbstractBaseFragment abstractBaseFragment) {
        abstractBaseFragment.setTracker(planYourExchangeModule.provideTracker());
        abstractBaseFragment.setServerApi(planYourExchangeModule.provideServerApi());
    }

    @Override
    public void inject(CourseOrSchoolFragment courseOrSchoolFragment) {
        courseOrSchoolFragment.setTracker(planYourExchangeModule.provideTracker());
    }
}
