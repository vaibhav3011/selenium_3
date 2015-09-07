package com.selenium.test;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vaibhav on 7/9/15.
 */
public class MultipleThread implements Runnable{

    String test, data, url, name;

    MultipleThread(String a, String b, String c, String d){
        this.test = a;
        this.data = b;
        this.url = c;
        this.name = d;
    }

    public void run() {

        ConfigDataTest temp = new ConfigDataTest();
        ArrayList<JSONObject> test_object = temp.setTest(test);
        JSONObject data_object = temp.setData(data);

        temp.RunTest(test_object, data_object, url, name);
    }
}
