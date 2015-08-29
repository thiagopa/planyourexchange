package com.planyourexchange.rest.model;

import java.math.BigDecimal;

/**
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
public class School extends BaseModel {

    private BigDecimal enrolmentFee;
    private BigDecimal booksFee;

    private String addressLine;
    private String suburb;
    private Integer zipCode;

    public BigDecimal getEnrolmentFee() {
        return enrolmentFee;
    }

    public void setEnrolmentFee(BigDecimal enrolmentFee) {
        this.enrolmentFee = enrolmentFee;
    }

    public BigDecimal getBooksFee() {
        return booksFee;
    }

    public void setBooksFee(BigDecimal booksFee) {
        this.booksFee = booksFee;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }
}
