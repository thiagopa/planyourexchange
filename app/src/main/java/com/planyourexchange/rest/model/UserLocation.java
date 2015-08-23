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

import android.location.Location;

import com.planyourexchange.utils.Constants;

/**
 * @author Thiago Pagonha
 * @version 20/08/15.
 */
public class UserLocation {

    public final double latitude;
    public final double longitude;
    public final double radius;

    public UserLocation(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        radius = Constants.DEFAULT_AIRPORT_RADIUS;
    }
}