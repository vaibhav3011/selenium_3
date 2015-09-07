package com.selenium.test;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

/**
 * Created by vaibhav on 5/9/15.
 */
public class go_to_detail {

    public boolean get(String scenario, String url, ChromeDriver driver, String expected_output, JSONObject data, PrintWriter writer) {

        driver.get(url+"/bangalore");

        WebElement myDynamicElement = (new WebDriverWait(driver, 40))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("car_0")));

        String current_class = this.getClass().getSimpleName();
        String output = expected_output;

        WebElement car_4 = driver.findElement(By.id("car_4"));
        car_4.click();

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        (new WebDriverWait(driver, 40))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("hero__details--title")));
        System.out.println(driver.getTitle());
        driver.close();
        driver.switchTo().window(tabs.get(0));
        driver.close();
        return true;
    }
}
