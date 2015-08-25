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

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Thiago Pagonha
 * @version 23/08/15.
 */
public class DateUtilsTest {

    @Test
    public void test_strip_seconds() {
        LocalTime time = new LocalTime(8,9,10);
        String result = DateUtils.toString(time);
        assertThat(result,is("08:09"));
    }

    @Test
    public void test_sum_and_timedelta() {
        LocalTime time = new LocalTime(20,30,0);

        Period result = DateUtils.sum(Period.ZERO,time,time,time);

        String timeDelta = DateUtils.toTimeDelta(result);

        assertThat(timeDelta, is("2 days 13 hours 30 minutes"));
    }
}
