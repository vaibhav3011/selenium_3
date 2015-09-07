package com.selenium.test;

/**
 * Created by vaibhav on 1/9/15.
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SeleniumHQTest {

    ConfigDataTest test = new ConfigDataTest();

    @Test
    public void testSetDataMethod() {

        String result_data = "{\"3.0\":[{\"datagroup\":\"3.0\",\"data\":\"{'c':3}\",\"sub_datagroup\":\"c\"}],\"1.0\":[{\"datagroup\":\"1.0\",\"data\":\"{'a':1}\",\"sub_datagroup\":\"a\"},{\"datagroup\":\"1.0\",\"data\":\"{'b':2}\",\"sub_datagroup\":\"b\"}]}";

        String data_object = test.setData("src/test/junit_data.xlsx").toString();

        assertEquals(result_data, data_object);
    }

    @Test
    public void testSetTestMethod() {

        String result_data = "[{\"b\":\"b1\",\"c\":\"c1\",\"a\":\"a1\"}, {\"d\":\"d2\",\"b\":\"b2\",\"c\":\"c2\",\"a\":\"a2\"}]";

        String test_array = test.setTest("src/test/junit_test.xlsx").toString();

        assertEquals(result_data, test_array);
    }

}
