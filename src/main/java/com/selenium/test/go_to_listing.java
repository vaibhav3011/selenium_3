//package com.selenium.test;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by vaibhav on 4/9/15.
// */
//public class go_to_listing {
//
//    public boolean get(String scenario, String url, ChromeDriver driver, String expected_output, JSONObject data, BufferedWriter writer) {
//
//        String current_class = this.getClass().getSimpleName();
//        String output = expected_output.split("=")[1];
//
//        try {
//            String input = data.getString(current_class).replace("'","\"");
//            JSONObject input_value = new JSONObject(input);
//
//            if(!input_value.has("model") || input_value.get("model").equals(" ")){
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " : model filter missing in datagroup " + data.get("datagroup") + "\n");
//                driver.close();
//                return false;
//            }
//            else if(!input_value.has("budget") || input_value.get("budget").equals(" ")){
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " : budget filter missing in datagroup " + data.get("datagroup") + "\n");
//                driver.close();
//                return false;
//            }
//            else if(!input_value.has("city") || input_value.get("city").equals(" ")){
//                writer.write(current_class +": city filter missing in datagroup " + data.get("datagroup")+"\n");
//                driver.close();
//                return false;
//            }
//            driver.get(url);
//
//            (new WebDriverWait(driver, 40))
//                    .until(ExpectedConditions.presenceOfElementLocated(By.className("video")));
//
//            if(driver.findElementsById("model_filter").size()>0){
//                WebElement model = driver.findElementById("model_filter");
//                model.click();
//                WebElement model_ul = model.findElement(By.className("dropdown")).findElement(By.className("row")).findElement(By.className("col-12")).findElement(By.className("clearlist"));
//                List<WebElement> model_li = model_ul.findElements(By.xpath(".//*"));
//                ArrayList<String> all_model = new ArrayList<String>();
//                for(int i = 0;i<model_li.size();i++) {
//                    String temp = model_li.get(i).getText();
//                    all_model.add(temp);
//                }
//                if(all_model.contains(input_value.get("model"))){
//                    model_li.get(all_model.indexOf(input_value.get("model"))).click();
//                }
//                else{
//                    writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " Given model value is not available in dropdown\n");
//                    driver.close();
//                    return false;
//                }
//            }
//            else{
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " unable to find element model_filter\n");
//                driver.close();
//                return false;
//            }
//
//            if(driver.findElementsById("budget_filter").size()>0){
//                WebElement budget = driver.findElementById("budget_filter");
//                budget.click();
//                WebElement budget_ul = budget.findElement(By.className("dropdown")).findElement(By.className("row")).findElement(By.className("col-12")).findElement(By.className("clearlist"));
//                List<WebElement> budget_li = budget_ul.findElements(By.xpath(".//*"));
//                ArrayList<String> all_budget = new ArrayList<String>();
//                for(int i = 0;i<budget_li.size();i++) {
//                    String temp = budget_li.get(i).getText();
//                    all_budget.add(temp);
//                }
//                if(all_budget.contains(input_value.get("budget"))){
//                    budget_li.get(all_budget.indexOf(input_value.get("budget"))).click();
//                }
//                else{
//                    writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " Given budget value is not available in dropdown\n");
//                    driver.close();
//                    return false;
//                }
//            }
//            else{
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " unable to find element budget_filter\n");
//                driver.close();
//                return false;
//            }
//
//            if(driver.findElementsById("city_filter").size()>0){
//                WebElement city = driver.findElementById("city_filter");
//                city.click();
//                WebElement city_ul = city.findElement(By.className("dropdown")).findElement(By.className("row")).findElement(By.className("col-12")).findElement(By.className("clearlist"));
//                List<WebElement> childs = city_ul.findElements(By.xpath(".//*"));
//                ArrayList<String> all_city = new ArrayList<String>();
//                for(int i = 0;i<childs.size();i++) {
//                    String temp = childs.get(i).getText();
//                    all_city.add(temp);
//                }
//                if(all_city.contains(input_value.get("city"))){
//                    childs.get(all_city.indexOf(input_value.get("city"))).click();
//                }
//                else{
//                    writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " Given city value is not available in dropdown\n");
//                    driver.close();
//                    return false;
//                }
//            }
//            else{
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " unable to find element city_filter\n");
//                driver.close();
//                return false;
//            }
//
//            if(driver.findElementsById("showCars").size()>0){
//                WebElement show_cars = driver.findElementById("showCars");
//                show_cars.click();
//            }
//            else{
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " unable to find show me cars button\n");
//                driver.close();
//                return false;
//            }
//
//
//            try{
//                Thread.sleep(2500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            String get_alerts = driver.getTitle();
//
//            if(get_alerts.equals(output)){
//                writer.append("Scenario " + scenario + " datagroup " + data.get("datagroup") + " " +this.getClass().getSimpleName() + ": Test pass  \n");
//            } else {
//                writer.append("Scenario " + scenario +" datagroup " + data.get("datagroup") + " " + this.getClass().getSimpleName() + ": Test fail  \n");
//            }
//            driver.close();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//}
