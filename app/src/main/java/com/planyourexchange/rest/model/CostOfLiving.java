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

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Thiago Pagonha
 * @version 13/08/15.
 */
public class CostOfLiving implements Serializable {
    private Integer id;

    private BigDecimal restaurantAveragePerMeal;
    private BigDecimal superMarketAveragePerMonth;
    private BigDecimal publicTransportMonthly;
    private BigDecimal rentAverageMonthly;
    private BigDecimal utilitesAverageMonthly;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getRestaurantAveragePerMeal() {
        return restaurantAveragePerMeal;
    }

    public void setRestaurantAveragePerMeal(BigDecimal restaurantAveragePerMeal) {
        this.restaurantAveragePerMeal = restaurantAveragePerMeal;
    }

    public BigDecimal getSuperMarketAveragePerMonth() {
        return superMarketAveragePerMonth;
    }

    public void setSuperMarketAveragePerMonth(BigDecimal superMarketAveragePerMonth) {
        this.superMarketAveragePerMonth = superMarketAveragePerMonth;
    }

    public BigDecimal getPublicTransportMonthly() {
        return publicTransportMonthly;
    }

    public void setPublicTransportMonthly(BigDecimal publicTransportMonthly) {
        this.publicTransportMonthly = publicTransportMonthly;
    }

    public BigDecimal getRentAverageMonthly() {
        return rentAverageMonthly;
    }

    public void setRentAverageMonthly(BigDecimal rentAverageMonthly) {
        this.rentAverageMonthly = rentAverageMonthly;
    }

    public BigDecimal getUtilitesAverageMonthly() {
        return utilitesAverageMonthly;
    }

    public void setUtilitesAverageMonthly(BigDecimal utilitesAverageMonthly) {
        this.utilitesAverageMonthly = utilitesAverageMonthly;
    }

}
