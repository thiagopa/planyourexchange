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
import com.planyourexchange.rest.model.School;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.planyourexchange.utils.MoneyUtils.newPrice;

/**
 * @author Thiago Pagonha
 * @version 04/09/15.
 */
public class SchoolActivity extends AdActivity {

    @Bind(R.id.school_details_name) TextView schoolName;
    @Bind(R.id.school_details_books_fee_value) TextView booksFee;
    @Bind(R.id.school_details_enrolment_fee_value) TextView enrolmentFee;

    @Inject
    PageFlowContext pageFlowContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // -- Inject dependecies first
        PlanYourExchangeApplication.getPlanYourExchangeComponent(this).inject(this);
        setContentView(R.layout.activity_school);

        ButterKnife.bind(this);

        School school = pageFlowContext.getSchoolCourseValue().getSchool();
        String currency = pageFlowContext.getCountry().getDefaultCurrency();

        schoolName.setText(school.getName());
        booksFee.setText(newPrice(currency,school.getBooksFee()));
        enrolmentFee.setText(newPrice(currency,school.getEnrolmentFee()));

        newAdView();
    }
}
