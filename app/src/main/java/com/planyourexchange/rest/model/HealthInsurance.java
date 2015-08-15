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
public class HealthInsurance extends BaseModel {
    private Country country;

    private String companyName;
    private String companyWebsite;

    private BigDecimal singlePricePerMonth;
    private BigDecimal couplePricePerMonth;
    private BigDecimal famillyPricePerMonth;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public BigDecimal getSinglePricePerMonth() {
        return singlePricePerMonth;
    }

    public void setSinglePricePerMonth(BigDecimal singlePricePerMonth) {
        this.singlePricePerMonth = singlePricePerMonth;
    }

    public BigDecimal getCouplePricePerMonth() {
        return couplePricePerMonth;
    }

    public void setCouplePricePerMonth(BigDecimal couplePricePerMonth) {
        this.couplePricePerMonth = couplePricePerMonth;
    }

    public BigDecimal getFamillyPricePerMonth() {
        return famillyPricePerMonth;
    }

    public void setFamillyPricePerMonth(BigDecimal famillyPricePerMonth) {
        this.famillyPricePerMonth = famillyPricePerMonth;
    }
}
