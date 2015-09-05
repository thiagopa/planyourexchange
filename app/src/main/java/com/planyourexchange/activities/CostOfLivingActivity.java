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

package com.planyourexchange.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.pageflow.PageFlowContext;
import com.planyourexchange.rest.model.CostOfLiving;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.planyourexchange.utils.MoneyUtils.newPrice;

/**
 * @author Thiago Pagonha
 * @version 04/09/15.
 */
public class CostOfLivingActivity extends AdActivity {

    @Bind(R.id.cost_of_living_init_text) TextView city;
    @Bind(R.id.cost_of_living_restaurant_value) TextView restaurant;
    @Bind(R.id.cost_of_living_supermarket_value) TextView superMarket;
    @Bind(R.id.cost_of_living_transport_ticket_value) TextView transport;
    @Bind(R.id.cost_of_living_rent_value) TextView rent;
    @Bind(R.id.cost_of_living_utilities_value) TextView utilities;

    @Inject
    PageFlowContext pageFlowContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- Inject dependecies first
        PlanYourExchangeApplication.getPlanYourExchangeComponent(this).inject(this);
        setContentView(R.layout.activity_cost_of_living);

        ButterKnife.bind(this);

        CostOfLiving costOfLiving = pageFlowContext.getCostOfLiving();
        String currency = pageFlowContext.getCountry().getDefaultCurrency();

        StringBuilder cityState = new StringBuilder()
                .append(pageFlowContext.getCity().getName())
                .append(",")
                .append(pageFlowContext.getCity().getState().getAbbreviation());

        city.setText(cityState.toString());
        restaurant.setText(newPrice(currency, costOfLiving.getRestaurantAveragePerMeal()));
        superMarket.setText(newPrice(currency, costOfLiving.getSuperMarketAveragePerMonth()));
        transport.setText(newPrice(currency, costOfLiving.getPublicTransportMonthly()));
        rent.setText(newPrice(currency, costOfLiving.getRentAverageMonthly()));
        utilities.setText(newPrice(currency, costOfLiving.getUtilitesAverageMonthly()));


        newAdView();
    }
}
