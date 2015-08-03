package com.planyourexchange.utils;

import com.planyourexchange.rest.model.SchoolCourseValueKey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by thiago on 03/08/15.
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
