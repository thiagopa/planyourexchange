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

import com.google.gson.Gson;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.planyourexchange.rest.adapter.MoneyTypeAdapterFactory;
import com.planyourexchange.rest.model.Country;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
public class MoneyTypeAdapterTest {

    Gson gson;

    @Before
    public void setUp() {

    }

    @Test
    public void testMoneyInside() {
        TypeAdapterFactory typeAdapterFactory = new MoneyTypeAdapterFactory();

        typeAdapterFactory.create(gson,TypeToken.get(Country.class));
    }
 }
