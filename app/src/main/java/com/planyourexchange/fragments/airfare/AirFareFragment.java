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

package com.planyourexchange.fragments.airfare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.planyourexchange.R;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.interfaces.SelectionListener;
import com.planyourexchange.locator.LocationService;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.AirFare;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public class AirFareFragment extends ListViewFragment<AirFareArgument,AirFare> {

    public AirFareFragment() {
        super(R.string.airfare_title,R.string.airfare_header,R.layout.airfare_list ,PageFlow.RESULT);
    }

    @Override
    protected Serializable createNextKey(AirFare airFare) {
        return null;
    }

    @Override
    protected void renderSingleModel(AirFare airFare, View rowView) {

    }

    @Override
    protected void saveOption(AirFare airFare) {
        pageFlowContext.setAirFare(airFare);
    }

    @Override
    protected void callService(AirFareArgument airFareArgument) {
        serverApi.getAirFares(airFareArgument.origin,airFareArgument.destination,this);
    }
}
