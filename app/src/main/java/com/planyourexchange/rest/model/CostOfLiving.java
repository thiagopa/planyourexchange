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

import java.math.BigDecimal;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
public class CostOfLiving {
    private Integer id;

    private City city;

    private Money restaurantAveragePerMeal;
    private Money superMarketAveragePerMonth;
    private Money publicTransportMonthly;
    private Money rentAverageMonthly;
    private Money utilitesAverageMonthly;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Money getRestaurantAveragePerMeal() {
        return restaurantAveragePerMeal;
    }

    public void setRestaurantAveragePerMeal(Money restaurantAveragePerMeal) {
        this.restaurantAveragePerMeal = restaurantAveragePerMeal;
    }

    public Money getSuperMarketAveragePerMonth() {
        return superMarketAveragePerMonth;
    }

    public void setSuperMarketAveragePerMonth(Money superMarketAveragePerMonth) {
        this.superMarketAveragePerMonth = superMarketAveragePerMonth;
    }

    public Money getPublicTransportMonthly() {
        return publicTransportMonthly;
    }

    public void setPublicTransportMonthly(Money publicTransportMonthly) {
        this.publicTransportMonthly = publicTransportMonthly;
    }

    public Money getRentAverageMonthly() {
        return rentAverageMonthly;
    }

    public void setRentAverageMonthly(Money rentAverageMonthly) {
        this.rentAverageMonthly = rentAverageMonthly;
    }

    public Money getUtilitesAverageMonthly() {
        return utilitesAverageMonthly;
    }

    public void setUtilitesAverageMonthly(Money utilitesAverageMonthly) {
        this.utilitesAverageMonthly = utilitesAverageMonthly;
    }
}
