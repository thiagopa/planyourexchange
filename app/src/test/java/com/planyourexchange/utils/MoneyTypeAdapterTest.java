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

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.planyourexchange.rest.adapter.MoneyTypeAdapter;
import com.planyourexchange.rest.model.Country;
import com.planyourexchange.rest.model.Money;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
public class MoneyTypeAdapterTest {

    Gson gson;

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Money.class, new MoneyTypeAdapter())
                .create();
    }

    @Test
    public void test_serialize() {
        Country country = gson.fromJson("{\"id\":14,\"visa_fee\":1.0,\"name\":\"New Zealand\",\"icon\":\"https://planyourexchange.s3.amazonaws.com/icons/220px-Flag_of_New_Zealand.svg.png\",\"visa_fee_currency\":\"NZD\",\"default_currency\":\"NZD\"}", Country.class);

        Money money = country.getVisaFee();

        assertEquals(new BigDecimal("1"), money.amount);
        assertEquals("NZD", money.currency);
    }
 }
