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

import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.MutablePeriod;
import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * @author Thiago Pagonha
 * @version 23/08/15.
 */
public class DateUtils {

    private static final String PATTERN = "HH:mm";
    private static final PeriodFormatter FORMATTER = new PeriodFormatterBuilder()
            .appendDays()
            .appendSuffix(" day"," days")
            .appendSeparator(" ")
            .appendHours()
            .appendSuffix(" hour", " hours")
            .appendSeparator(" ")
            .appendMinutes()
            .appendSuffix(" minute", " minutes")
            .toFormatter();

    public static String toString(LocalTime localTime) {
        if(localTime!=null) {
            return localTime.toString(PATTERN);
        }
        return null;
    }

    public static Period sum(Period timeTotal,LocalTime...locatimes) {
        for (LocalTime time : locatimes) {
            timeTotal = timeTotal.plusHours(time.getHourOfDay());
            timeTotal = timeTotal.plusMinutes(time.getMinuteOfHour());
            timeTotal = timeTotal.plusSeconds(time.getSecondOfMinute());
        }
        return timeTotal;
    }

    public static String toTimeDelta(Period timeTotal) {
        return timeTotal.normalizedStandard().toString(FORMATTER);
    }
}
