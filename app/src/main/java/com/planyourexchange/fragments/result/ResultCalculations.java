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

import com.planyourexchange.rest.model.CostOfLiving;
import com.planyourexchange.rest.model.School;

import org.joda.time.DurationFieldType;
import org.joda.time.LocalDate;
import org.joda.time.Months;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Weeks;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.planyourexchange.utils.MoneyUtils.newPrice;

/**
 * Need to externalize this for comprehension sake
 * @author Thiago Pagonha
 * @version 24/08/15.
 */
public class ResultCalculations {
    private static final BigDecimal TWO_MEALS = new BigDecimal("2");
    private final String defaultCurrency;
    private BigDecimal numberOfWeeks;
    private BigDecimal months;

    public ResultCalculations(String defaultCurrency, Integer numberOfWeeks) {
        this.defaultCurrency = defaultCurrency;
        setNumberOfWeeks(numberOfWeeks);
    }

    public void setNumberOfWeeks(Integer numberOfWeeks) {
        this.numberOfWeeks = new BigDecimal(numberOfWeeks);
        LocalDate localDate = LocalDate.now();
        Months months = Months.monthsBetween(localDate,localDate.plusWeeks(numberOfWeeks));
        this.months = new BigDecimal(months.getMonths());
    }


    public String totalCourseCost(BigDecimal weekPrice, School school) {
        return newPrice(defaultCurrency, weekPrice.multiply(numberOfWeeks).add(school.getBooksFee()).add(school.getEnrolmentFee()));
    }

    public String totalInsuranceCost(BigDecimal pricePerMonth) {
        return newPrice(defaultCurrency, pricePerMonth.multiply(months));
    }

    public String totalCostOfLiving(CostOfLiving costOfLiving) {
        // -- Restaurant 2 meals a day
        BigDecimal totalRestaurantCost = costOfLiving.getRestaurantAveragePerMeal().multiply(TWO_MEALS).multiply(numberOfWeeks);
        BigDecimal totalMonthlyCost = costOfLiving.getPublicTransportMonthly()
                .add(costOfLiving.getRentAverageMonthly())
                .add(costOfLiving.getSuperMarketAveragePerMonth())
                .add(costOfLiving.getUtilitesAverageMonthly())
                .multiply(months);

        return newPrice(defaultCurrency, totalRestaurantCost.add(totalMonthlyCost));
    }
}
