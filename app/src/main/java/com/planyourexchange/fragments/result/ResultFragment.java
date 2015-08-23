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
import com.planyourexchange.interfaces.SelectionListener;
import com.planyourexchange.pageflow.PageFlowContext;
import com.planyourexchange.rest.model.AirFare;
import com.planyourexchange.utils.Constants;
import com.planyourexchange.utils.MoneyUtils;

import java.math.BigDecimal;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Thiago Pagonha
 * @version 17/08/15.
 */
public class ResultFragment extends Fragment implements SelectionListener {

    @Inject PageFlowContext pageFlowContext;
    @Inject Tracker tracker;

    @Bind(R.id.result_country_state_city) TextView countryStateCity;
    @Bind(R.id.result_course_school)TextView courseSchool;
    @Bind(R.id.result_cost_for_weeks) TextView costForWeeks;
    @Bind(R.id.result_health_insurance) TextView healthInsurance;
    @Bind(R.id.result_airfare) TextView airFare;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlanYourExchangeApplication.getPlanYourExchangeComponent(getActivity()).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        // -- Send tracking information to Google Analytics so I know which screen users are browsing
        tracker.setScreenName("Result");
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_fragment, container, false);
        ButterKnife.bind(this, view);
        populateResults();
        return view;
    }

    private void populateResults() {

        int numberOfWeeks = pageFlowContext.getCourse().getWeekDuration();
        BigDecimal totalCourseCost = pageFlowContext.getSchoolCourseValue().getWeekPrice().multiply(new BigDecimal(numberOfWeeks));
        BigDecimal totalInsuranceCost = pageFlowContext.getHealthInsurance().getSinglePricePerMonth().multiply(new BigDecimal(numberOfWeeks));
        String defaultCurrency = pageFlowContext.getCountry().getDefaultCurrency();

        StringBuilder countryStateCityText = new StringBuilder()
                .append(pageFlowContext.getCountry().getName())
                .append("/")
                .append(pageFlowContext.getCity().getState().getName())
                .append("/")
                .append(pageFlowContext.getCity().getName());

        StringBuilder courseSchoolText = new StringBuilder()
                .append(pageFlowContext.getCourse().getName())
                .append("/")
                .append(pageFlowContext.getSchoolCourseValue().getSchool().getName());

        StringBuilder costForWeeksText = new StringBuilder()
                .append(MoneyUtils.newPrice(defaultCurrency,totalCourseCost))
                .append(" for ")
                .append(numberOfWeeks)
                .append(" weeks");

        StringBuilder healthInsuranceText = new StringBuilder()
                .append(pageFlowContext.getHealthInsurance().getName())
                .append(" for ")
                .append(numberOfWeeks)
                .append(" weeks costs ")
                .append(MoneyUtils.newPrice(defaultCurrency,totalInsuranceCost));

        countryStateCity.setText(countryStateCityText.toString());
        courseSchool.setText(courseSchoolText.toString());
        costForWeeks.setText(costForWeeksText.toString());
        healthInsurance.setText(healthInsuranceText.toString());
    }


    @Override
    public void updateView(Bundle bundle) {
        AirFare airFare = (AirFare) bundle.get(Constants.KEY_ID);
        this.airFare.setText(MoneyUtils.newPrice(airFare.getPriceCurrency(),airFare.getPrice()));
    }

    // -- This is the last view, doesn't need to be cleared
    @Override
    public void clearView() {}

}
