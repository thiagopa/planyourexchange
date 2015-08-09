package com.planyourexchange.utils;

import com.planyourexchange.rest.model.SchoolCourseValueKey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

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
public class SchoolCourseValueKeyTest {



   @Test
   public void testSameObject() {
       SchoolCourseValueKey o1 = new SchoolCourseValueKey();
       o1.setCityId(1);
       o1.setCourseId(1);
       o1.setSchoolId(1);

       SchoolCourseValueKey o2 = new SchoolCourseValueKey();
       o2.setCityId(1);
       o2.setCourseId(1);
       o2.setSchoolId(1);

       boolean result = o1.equals(o2);

       assertTrue(result);
   }

    @Test
    public void testDifferentCity() {
        SchoolCourseValueKey o1 = new SchoolCourseValueKey();
        o1.setCityId(1);

        SchoolCourseValueKey o2 = new SchoolCourseValueKey();
        o2.setCityId(2);

        boolean result = o1.equals(o2);

        assertFalse(result);
    }

    @Test
    public void testSameCityDifferentCourseObject() {
        SchoolCourseValueKey o1 = new SchoolCourseValueKey();
        o1.setCityId(1);
        o1.setCourseId(1);

        SchoolCourseValueKey o2 = new SchoolCourseValueKey();
        o2.setCityId(1);
        o2.setCourseId(2);

        boolean result = o1.equals(o2);

        assertFalse(result);
    }

    @Test
    public void testSameCityDifferentSchoolObject() {
        SchoolCourseValueKey o1 = new SchoolCourseValueKey();
        o1.setCityId(1);
        o1.setSchoolId(1);

        SchoolCourseValueKey o2 = new SchoolCourseValueKey();
        o2.setCityId(1);
        o2.setSchoolId(2);

        boolean result = o1.equals(o2);

        assertFalse(result);
    }

}
