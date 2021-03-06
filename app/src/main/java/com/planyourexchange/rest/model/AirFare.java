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

package com.planyourexchange.rest.model;

import org.joda.time.LocalDate;
import org.parceler.Parcel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Thiago Pagonha
 * @version 20/08/15.
 */
@Parcel
public class AirFare extends BaseAir implements Comparable<AirFare> {
    LocalDate date;

    BigDecimal price;
    String priceCurrency;

    List<AirTrip> airTrips;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public List<AirTrip> getAirTrips() {
        return airTrips;
    }

    public void setAirTrips(List<AirTrip> airTrips) {
        this.airTrips = airTrips;
    }

    @Override
    public int compareTo(AirFare another) {
        return price.compareTo(another.getPrice());
    }
}
