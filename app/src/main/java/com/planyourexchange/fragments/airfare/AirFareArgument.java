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

package com.planyourexchange.fragments.airfare;

import java.io.Serializable;

/**
 * @author Thiago Pagonha
 * @version 21/08/15.
 */
public class AirFareArgument implements Serializable {
    public final String origins;
    public final String destination;

    public AirFareArgument(String[] origins, String destination) {

        if(origins.length==0) {
            throw new IllegalArgumentException("At least one airport is needed");
        }

        StringBuilder builder = new StringBuilder();

        for (String origin: origins) {
            builder.append(origin);
            builder.append(",");
        }

        builder.deleteCharAt(builder.length()-1);

        this.origins = builder.toString();
        this.destination = destination;
    }

    @Override
    public boolean equals(Object object) {
        AirFareArgument o = (AirFareArgument)object;
        return origins.equals(o.origins) && destination.equals(o.destination);
    }

    @Override
    public int hashCode() {
        return origins.hashCode() + destination.hashCode();
    }
}
