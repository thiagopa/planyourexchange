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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.fragments.airfare.AirFareArgument;
import com.planyourexchange.locator.LocationService;
import com.planyourexchange.pageflow.PageFlow;
import com.planyourexchange.fragments.base.ListViewFragment;
import com.planyourexchange.rest.model.HealthInsurance;
import com.planyourexchange.utils.MoneyUtils;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Thiago Pagonha
 * @version 15/08/15.
 */
public class HealthInsurancesFragment extends ListViewFragment<Integer,HealthInsurance> {

    @Inject
    LocationService locationService;

    public HealthInsurancesFragment() {
        super(R.string.health_insurances_title,R.string.health_insurances_header,R.layout.health_insurance_list ,PageFlow.AIR_FARE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlanYourExchangeApplication.getPlanYourExchangeComponent(getActivity()).inject(this);
    }

    @Override
    public void callService(Integer countryId) {
        serverApi.listHealthInsurances(countryId,this);
    }

    @Override
    protected Object createNextKey(HealthInsurance healthInsurance) {
        String[] nearbyAirports = locationService.getAirports();
        String destination = pageFlowContext.getCity().getAirport();

        return new AirFareArgument(nearbyAirports,destination);
    }

    static class ViewHolder {
        @Bind(R.id.health_insurance_icon) ImageView icon;
        @Bind(R.id.health_insurance_name) TextView name;
        @Bind(R.id.health_insurance_single_price) TextView singlePrice;
        @Bind(R.id.health_insurance_couple_price) TextView couplePrice;
        @Bind(R.id.health_insurance_family_price) TextView familyPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    protected void renderSingleModel(HealthInsurance healthInsurance, View rowView) {

        ViewHolder viewHolder = new ViewHolder(rowView);

        ImageLoader.getInstance().displayImage(healthInsurance.getIcon(), viewHolder.icon);

        viewHolder.name.setText(healthInsurance.getName());

        String defaultCurrency = healthInsurance.getCountry().getDefaultCurrency();

        viewHolder.singlePrice.setText(MoneyUtils.newPrice(
                        defaultCurrency,
                        healthInsurance.getSinglePricePerMonth())
        );

        viewHolder.couplePrice.setText(MoneyUtils.newPrice(
                        defaultCurrency,
                        healthInsurance.getCouplePricePerMonth())
        );

        viewHolder.familyPrice.setText(MoneyUtils.newPrice(
                        defaultCurrency,
                        healthInsurance.getFamillyPricePerMonth())
        );
    }

    @Override
    protected void saveOption(HealthInsurance healthInsurance) {
        pageFlowContext.setHealthInsurance(healthInsurance);
    }
}
