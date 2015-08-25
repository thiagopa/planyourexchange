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

package com.planyourexchange.utils;

import com.planyourexchange.fragments.result.ResultCalculations;
import com.planyourexchange.rest.model.CostOfLiving;
import com.planyourexchange.rest.model.School;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;


import java.math.BigDecimal;

/**
 * @author Thiago Pagonha
 * @version 24/08/15.
 */
public class ResultCalculationsTest {

    ResultCalculations calculations;

    @Before
    public void setUp() {
        calculations = new ResultCalculations("USD",50);
    }

    @Test
    public void testTotalInsuranceCost() {
        String result = calculations.totalInsuranceCost(new BigDecimal("47.5"));
        assertThat(result,is("USD 522.50"));
    }

    @Test
    public void testTotalCourseCost() {
        School school = new School();
        school.setBooksFee(new BigDecimal("110"));
        school.setEnrolmentFee(new BigDecimal("280"));
        String result = calculations.totalCourseCost(new BigDecimal("220"), school);
        assertThat(result,is("USD 11390.00"));
    }

    @Test
    public void testTotalCostOfLiving() {
        CostOfLiving costOfLiving = new CostOfLiving();
        costOfLiving.setPublicTransportMonthly(new BigDecimal("400"));
        costOfLiving.setRentAverageMonthly(new BigDecimal("2000"));
        costOfLiving.setSuperMarketAveragePerMonth(new BigDecimal("438"));
        costOfLiving.setUtilitesAverageMonthly(new BigDecimal("80"));
        costOfLiving.setRestaurantAveragePerMeal(new BigDecimal("20.5"));

        String result = calculations.totalCostOfLiving(costOfLiving);
        assertThat(result,is("USD 34148.00"));
    }


}
