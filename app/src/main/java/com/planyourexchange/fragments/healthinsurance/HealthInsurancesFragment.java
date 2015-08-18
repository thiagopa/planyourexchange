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

package com.planyourexchange.fragments.healthinsurance;

import com.planyourexchange.R;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.rest.model.HealthInsurance;

/**
 * @author Thiago Pagonha
 * @version 15/08/15.
 */
public class HealthInsurancesFragment extends ListViewFragment<Integer,HealthInsurance> {
    public HealthInsurancesFragment() {
        super(R.string.health_insurances_title,R.string.health_insurances_header, PageFlow.AIR_FARE);
    }

    @Override
    public void callService(Integer countryId) {
        serverApi.listHealthInsurances(countryId,this);
    }

}
