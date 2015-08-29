package com.planyourexchange.fragments.costofliving;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.planyourexchange.R;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.rest.model.CostOfLiving;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.planyourexchange.utils.MoneyUtils.newPrice;

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
public class CostOfLivingFragment extends AbstractBaseFragment<Integer,CostOfLiving,RelativeLayout> {

    static class ViewHolder {
        @Bind(R.id.cost_of_living_init_text) TextView city;
        @Bind(R.id.cost_of_living_restaurant_value) TextView restaurant;
        @Bind(R.id.cost_of_living_supermarket_value) TextView superMarket;
        @Bind(R.id.cost_of_living_transport_ticket_value) TextView transport;
        @Bind(R.id.cost_of_living_rent_value) TextView rent;
        @Bind(R.id.cost_of_living_utilities_value) TextView utilites;

        ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    public CostOfLivingFragment() {
        super(R.string.cost_of_living_title, R.layout.cost_of_living_fragment, R.id.cost_of_living_relative_layout);
    }

    @Override
    protected void callService(Integer cityId) {
        serverApi.getCostOfLiving(cityId, this);
    }


    @Override
    protected void drawModel(CostOfLiving costOfLiving, RelativeLayout linearLayout) {
        /*
        String currency = costOfLiving.getCity().getCountry().getDefaultCurrency();

        ViewHolder viewHolder = new ViewHolder(linearLayout);

        viewHolder.city.setText(costOfLiving.getCity().toString());
        viewHolder.restaurant.setText(newPrice(currency, costOfLiving.getRestaurantAveragePerMeal()));
        viewHolder.superMarket.setText(newPrice(currency, costOfLiving.getSuperMarketAveragePerMonth()));
        viewHolder.transport.setText(newPrice(currency, costOfLiving.getPublicTransportMonthly()));
        viewHolder.rent.setText(newPrice(currency, costOfLiving.getRentAverageMonthly()));
        viewHolder.utilites.setText(newPrice(currency, costOfLiving.getUtilitesAverageMonthly()));
        */
    }
}
