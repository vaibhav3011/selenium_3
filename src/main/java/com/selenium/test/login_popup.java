package com.selenium.test;

import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by vaibhav on 5/9/15.
 */
public class login_popup {

    public void get(String scenario, String url, String expected_output, JSONObject data) {
        ChromeDriver driver = new ChromeDriver();

//        String current_class = this.getClass().getSimpleName();
//        String output = expected_output.split("=")[1];
//
//        driver.get(url + "/bangalore");
//
//        try {
//            Thread.sleep(3000);                 //1000 milliseconds is one second.
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
//
//        WebElement show_cars = driver.findElementById("login");
//        show_cars.click();
//
//        try {
//            Thread.sleep(1000);                 //1000 milliseconds is one second.
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
//
//        String login_text = driver.getTitle();
        System.out.println("login_popup" + "  " +scenario + "  " + url + "  ");
        driver.close();
    }
}
