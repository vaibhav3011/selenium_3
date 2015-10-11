//package com.selenium.test;
//
//import org.json.JSONObject;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//
///**
// * Created by vaibhav on 7/9/15.
// */
//public class get_alert {
//
//    public boolean get(String scenario, String url, ChromeDriver driver, String expected_output, JSONObject data, BufferedWriter writer) {
//
//        String output = expected_output.split("=")[1];
//        driver.get(url + "/bangalore");
//
//        try {
//            if (driver.findElementsById("getAlerts").size() > 0) {
//                WebElement get_alert = driver.findElement(By.id("getAlerts"));
//
//                get_alert.click();
//                Thread.sleep(500);
//                if (driver.findElementsById("login_message").size() > 0) {
//                    WebElement message = driver.findElement(By.id("login_message"));
//                    String alert_message = message.getText();
//
//                    if(output.equals(alert_message)){
//                        writer.append("Scenario " + scenario + " " + this.getClass().getSimpleName() + " :Test pass\n");
//                        driver.close();
//                    }
//                    else{
//                        writer.append("Scenario " + scenario + " " + this.getClass().getSimpleName() + " :Test fail\n");
//                        driver.close();
//                        return false;
//                    }
//                }
//                else{
//                    writer.append("Scenario " + scenario + " " + this.getClass().getSimpleName() + " :Test fail\n");
//                    driver.close();
//                    return false;
//                }
//            } else {
//                writer.append("Scenario " + scenario + " " + this.getClass().getSimpleName() + " unable to find get alerts button\n");
//                driver.close();
//                return false;
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//}
