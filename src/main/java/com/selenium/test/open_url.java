package com.selenium.test;

import org.json.JSONObject;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by vaibhav on 2/9/15.
 */
public class open_url {


    public void get(String scenario, String url, ChromeDriver driver, String expected_output, JSONObject data, BufferedWriter writer) {
        driver.get(url);
        String output = expected_output.split("=")[1];
        String title = driver.getTitle();

        if(title.equals(output)){
            try {
                writer.append(this.getClass().getSimpleName() + " " + url + " : Test pass\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                writer.append(this.getClass().getSimpleName() + " " + url + ": Test fail\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.close();
    };
}
