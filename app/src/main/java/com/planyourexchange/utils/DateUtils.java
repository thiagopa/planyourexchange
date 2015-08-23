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

import org.joda.time.LocalTime;
import org.joda.time.MutablePeriod;
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
            .appendSuffix("days")
            .appendSeparatorIfFieldsAfter(" ")
            .appendHours()
            .appendSuffix(":")
            .appendMinutes()
            .appendSuffix(":")
            .toFormatter();

    public static String toString(LocalTime localTime) {
        if(localTime!=null) {
            return localTime.toString(PATTERN);
        }
        return null;
    }

    public static void sum(MutablePeriod timeTotal,LocalTime...locatimes) {
        for (LocalTime time : locatimes) {
            timeTotal.addHours(time.getHourOfDay());
            timeTotal.addMinutes(time.getMinuteOfHour());
            timeTotal.addSeconds(time.getSecondOfMinute());
        }
    }

    public static String toString(MutablePeriod mutablePeriod) {
        return mutablePeriod.toString(FORMATTER);
    }
}
