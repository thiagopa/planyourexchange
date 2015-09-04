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

package com.planyourexchange.fragments.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.planyourexchange.R;
import com.planyourexchange.app.PlanYourExchangeApplication;
import com.planyourexchange.fragments.base.AbstractBaseFragment;
import com.planyourexchange.interfaces.SelectionListener;
import com.planyourexchange.pageflow.PageFlowContext;
import com.planyourexchange.rest.model.AirFare;
import com.planyourexchange.rest.model.CostOfLiving;
import com.planyourexchange.utils.Constants;
import com.planyourexchange.utils.MoneyUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * The results page which will have all calculation
 * Actually has a final rest call to identify the cost of living for the given city
 * Could have done this before, but this is better
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public class ResultFragment extends AbstractBaseFragment<Integer,CostOfLiving,TextView> {

    @Bind(R.id.result_country_state_city) TextView countryStateCity;
    @Bind(R.id.result_visa_fee) TextView visaFee;
    @Bind(R.id.result_course_school)TextView courseSchool;
    @Bind(R.id.result_cost_for_weeks) TextView costForWeeks;
    @Bind(R.id.result_health_insurance) TextView healthInsurance;
    @Bind(R.id.result_airfare) TextView airFare;
    @Bind(R.id.result_total_cost) TextView totalCost;

    private ResultCalculations resultCalculations;

    public ResultFragment() {
        super(R.string.result_title, R.layout.result_fragment, R.id.result_cost_of_living);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void drawModel(CostOfLiving costOfLiving, TextView textView) {

        BigDecimal totalExchangeCost = BigDecimal.ZERO;

        String defaultCurrency = pageFlowContext.getCountry().getDefaultCurrency();
        Integer numberOfWeeks = pageFlowContext.getSchoolCourseValue().getCourse().getWeekDuration();

        resultCalculations = new ResultCalculations(numberOfWeeks);

        BigDecimal totalCourseCost = resultCalculations.totalCourseCost(pageFlowContext.getSchoolCourseValue().getWeekPrice(), pageFlowContext.getSchoolCourseValue().getSchool());
        // -- Choose option, default single
        BigDecimal totalInsuranceCost = resultCalculations.totalInsuranceCost(pageFlowContext.getHealthInsurance().getSinglePricePerMonth());

        StringBuilder countryStateCityText = new StringBuilder()
                .append(pageFlowContext.getCity().getName())
                .append(",")
                .append(pageFlowContext.getCity().getState().getAbbreviation())
                .append(",")
                .append(pageFlowContext.getCountry().getName());


        StringBuilder courseSchoolText = new StringBuilder()
                .append(pageFlowContext.getCourse().getName())
                .append(" at ")
                .append(pageFlowContext.getSchoolCourseValue().getSchool().getName());

        StringBuilder costForWeeksText = new StringBuilder()
                .append(MoneyUtils.newPrice(defaultCurrency, totalCourseCost))
                .append(" for ")
                .append(numberOfWeeks)
                .append(" weeks");

        StringBuilder healthInsuranceText = new StringBuilder()
                .append(pageFlowContext.getHealthInsurance().getName())
                .append(" for ")
                .append(numberOfWeeks)
                .append(" weeks costs ")
                .append(MoneyUtils.newPrice(defaultCurrency, totalInsuranceCost));

        AirFare airFare = pageFlowContext.getAirFare();

        StringBuilder airFareText = new StringBuilder()
                .append("Airfare from ")
                .append(airFare.getOriginAirport())
                .append(" to ")
                .append(airFare.getDestinationAirport())
                .append(" costs ")
                .append(MoneyUtils.newPrice(airFare.getPriceCurrency(), airFare.getPrice()));

        this.airFare.setText(airFareText.toString());

        countryStateCity.setText(countryStateCityText.toString());
        visaFee.setText(MoneyUtils.newPrice(defaultCurrency, pageFlowContext.getCountry().getVisaFee()));
        courseSchool.setText(courseSchoolText.toString());
        costForWeeks.setText(costForWeeksText.toString());
        healthInsurance.setText(healthInsuranceText.toString());

        BigDecimal totalCostOfLiving = resultCalculations.totalCostOfLiving(costOfLiving);
        textView.setText(MoneyUtils.newPrice(defaultCurrency,totalCostOfLiving));

        totalExchangeCost = totalCostOfLiving.add(totalCourseCost)
                .add(totalInsuranceCost)
                .add(pageFlowContext.getCountry().getVisaFee());

        totalCost.setText(MoneyUtils.newPrice(defaultCurrency,totalExchangeCost));
    }

    @Override
    protected void callService(Integer cityId) {
        serverApi.getCostOfLiving(cityId, this);
    }

}
