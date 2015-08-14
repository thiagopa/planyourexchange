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

package com.planyourexchange.rest.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.planyourexchange.rest.model.Money;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
public class MoneyTypeAdapter extends TypeAdapter<Money> {
    @Override
    public void write(JsonWriter out, Money value) throws IOException {
        // I don't give a shit
    }

    @Override
    public Money read(JsonReader in) throws IOException {

        String amountPath = in.getPath();
        String currencyPath = amountPath.substring(amountPath.indexOf(".") + 1, amountPath.length()) + "_currency";

        BigDecimal amount = new BigDecimal(in.nextDouble());

        while(in.hasNext()) {
            if(in.nextName().equals(currencyPath)) {
                String currency = in.nextString();

                return new Money(amount, currency);
            }
            in.skipValue();
        }

        throw new IllegalStateException("Couldn't find currency value!!!");
    }
}
