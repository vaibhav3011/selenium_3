package com.selenium.test;

/**
 * Created by vaibhav on 1/9/15.
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class HomeClass {

    public static WebDriver driver;
    public static String test_config;
    public static Iterator<Row> test_sheet_rowIterator;
    public static void main(String[] args)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            LineIterator it = IOUtils.lineIterator(br);
            for (int lineNumber = 0; it.hasNext(); lineNumber++) {
                if(lineNumber==0){
                    test_config=(String) it.next();
                }
            }

            //Open and read test config file
            //Get path for test_sheet, data_sheet and url to test

            FileInputStream test_conf_file = new FileInputStream(new File(test_config));
            XSSFWorkbook test_conf_workbook = new XSSFWorkbook(test_conf_file);
            XSSFSheet test_conf_sheet = test_conf_workbook.getSheetAt(0);
            Iterator<Row> test_conf_rowIterator = test_conf_sheet.iterator();

            test_conf_rowIterator.next();

            Row row = test_conf_rowIterator.next();

            String test_sheet = row.getCell(0).getStringCellValue();
            String data_sheet = row.getCell(1).getStringCellValue();
            String url = row.getCell(2).getStringCellValue();

            ConfigDataTest data_test = new ConfigDataTest();

            ArrayList<JSONObject> test_object = data_test.setTest(test_sheet);
            JSONObject data_object = data_test.setData(data_sheet);

            data_test.RunTest(test_object, data_object, url);

        } catch (FileNotFoundException e) {
            System.out.println("Test config file not found");
        } catch (IOException e) {
            System.out.println("Test config file read exception");
        }
    }
}