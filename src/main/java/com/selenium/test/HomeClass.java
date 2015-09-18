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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeClass {

    public static WebDriver driver;
    public static String test_config;
    public static Iterator<Row> test_sheet_rowIterator;
    public static void main(String[] args)
    {
        //Set all browsers pre requirements
        File chromedriver = new File("drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

        try
        {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));
            LineIterator it = IOUtils.lineIterator(br);
            for (int lineNumber = 0; it.hasNext(); lineNumber++) {
                if(lineNumber==0){
                    test_config=(String) it.next();
                }
            }

            System.out.println(test_config);

            //Open and read test config file
            //Get path for test_sheet, data_sheet and url to test

            FileInputStream test_conf_file = new FileInputStream(new File(test_config));
            XSSFWorkbook test_conf_workbook = new XSSFWorkbook(test_conf_file);
            XSSFSheet test_conf_sheet = test_conf_workbook.getSheetAt(0);
            Iterator<Row> test_conf_rowIterator = test_conf_sheet.iterator();

            Row row = test_conf_rowIterator.next();

            ArrayList<Runnable> class_list = new ArrayList<Runnable>();
            for(int a = 0;a<test_conf_sheet.getLastRowNum();a++) {
                row = test_conf_rowIterator.next();
                class_list.add(new MultipleThread(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), row.getCell(4).getStringCellValue()));
            }

            ExecutorService executor = Executors.newFixedThreadPool(class_list.size());
            for(int list=0;list<class_list.size();list++){
                executor.execute(class_list.get(list));
            }

            executor.shutdown();
            // Wait until all threads are finish
            while (!executor.isTerminated()) {

            }

        } catch (FileNotFoundException e) {
            System.out.println("Test config file not found");
        } catch (IOException e) {
            System.out.println("Test config file read exception");
        }
    }
}