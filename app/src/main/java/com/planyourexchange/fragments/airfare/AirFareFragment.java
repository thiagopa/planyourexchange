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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.planyourexchange.R;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.interfaces.SelectionListener;
import com.planyourexchange.locator.LocationService;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.rest.model.AirFare;
import com.planyourexchange.rest.model.AirTrip;
import com.planyourexchange.rest.model.SchoolCourseValue;
import com.planyourexchange.rest.model.SchoolCourseValueKey;
import com.planyourexchange.utils.MoneyUtils;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    static class ViewHolder {
        @Bind(R.id.airfare_price) TextView price;
        @Bind(R.id.airfare_origin) TextView origin;
        @Bind(R.id.airfare_destination) TextView destination;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        static class RowHolder {
            @Bind(R.id.airtrip_operated_by) TextView operatedBy;
            @Bind(R.id.airfare_origin) TextView origin;
            @Bind(R.id.airfare_destination) TextView destination;
            @Bind(R.id.airtrip_flight_duration) TextView flightDuration;
            @Bind(R.id.airtrip_flight_layover) TextView flightLayover;

            RowHolder (View view) {
                ButterKnife.bind(this,view);
            }
        }
    }

    @Override
    protected void renderSingleModel(AirFare airFare, View rowView) {
        ViewHolder viewHolder = new ViewHolder(rowView);

        viewHolder.price.setText(MoneyUtils.newPrice(airFare.getPriceCurrency(),airFare.getPrice()));
        viewHolder.origin.setText(airFare.getOrigin());
        viewHolder.destination.setText(airFare.getDestination());

        for(AirTrip airtrip : airFare.getAirTrips()) {
            LayoutInflater inflater = (LayoutInflater) getLayoutInflater(null);
            View airTripRow = inflater.inflate(R.layout.airtrip_list, null, true);
            ViewHolder.RowHolder rowHolder = new ViewHolder.RowHolder(airTripRow);

            rowHolder.operatedBy.setText(airtrip.getOperatedBy());
            rowHolder.origin.setText(airtrip.getOrigin());
            rowHolder.destination.setText(airtrip.getDestination());
            rowHolder.flightDuration.setText(airtrip.getFlightDuration().toString());
            rowHolder.flightLayover.setText(airtrip.getAirportLayover().toString());

            ((ViewGroup)rowView).addView(airTripRow);
        }

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
