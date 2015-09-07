package com.selenium.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vaibhav on 4/9/15.
 */
public class ConfigDataTest {

    public JSONObject setData(String sheet){

        JSONObject data_sheet = new JSONObject();
        ArrayList<String> dataTitle = new ArrayList<String>();
        ArrayList<String> datagroup_name = new ArrayList<String>();

        FileInputStream data_conf_file = null;
        try {
            data_conf_file = new FileInputStream(new File(sheet));
            XSSFWorkbook test_workbook = new XSSFWorkbook(data_conf_file);
            XSSFSheet test_sheet = test_workbook.getSheetAt(0);
            Iterator<Row> test_rowIterator = test_sheet.iterator();

            Row row = test_rowIterator.next();
            for (Cell cell : row) {
                dataTitle.add(cell.getStringCellValue());
                // Do something here
            }

            String current_datagroup = "";
            ArrayList<JSONObject> temp_array = new ArrayList<JSONObject>();
            for(int row_test=0; row_test < test_sheet.getLastRowNum(); row_test++) {
                row = test_rowIterator.next();
                if(row.getCell(0)!=null && row.getCell(0).toString().equals("##")){
                    data_sheet.put(current_datagroup, temp_array);
                    temp_array = new ArrayList<JSONObject>();
                    break;
                }
                else if((row.getCell(0)!=null && row.getCell(0).toString().equals("#"))){

                    data_sheet.put(current_datagroup, temp_array);
                    temp_array = new ArrayList<JSONObject>();
                    continue;
                }
                else{
                    JSONObject temp = new JSONObject();

                    for (int cell_index = 0; cell_index <row.getLastCellNum();cell_index++) {

                        if(row.getCell(cell_index) != null) {
                            temp.put(dataTitle.get(cell_index), row.getCell(cell_index).toString());
                        }
                    }
                    temp_array.add(temp);
                    current_datagroup = row.getCell(0).toString();
                    if(!datagroup_name.contains(current_datagroup)){
                        datagroup_name.add(current_datagroup);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data_sheet;
    };

    public ArrayList<JSONObject> setTest(String sheet){

        ArrayList<String> testTitle = new ArrayList<String>();
        ArrayList<JSONObject> test_sheet_data = new ArrayList<JSONObject>();

        FileInputStream test_conf_file = null;
        try {
            test_conf_file = new FileInputStream(new File(sheet));
            XSSFWorkbook test_workbook = new XSSFWorkbook(test_conf_file);
            XSSFSheet test_sheet = test_workbook.getSheetAt(0);
            Iterator<Row> test_rowIterator = test_sheet.iterator();

            Row row = test_rowIterator.next();
            for (Cell cell : row) {
                testTitle.add(cell.getStringCellValue());
                // Do something here
            }

            for(int row_test=0; test_rowIterator.hasNext(); row_test++) {
                row = test_rowIterator.next();
                JSONObject temp = new JSONObject();

                for (int cell_index = 0; cell_index <row.getLastCellNum();cell_index++) {

                    if(row.getCell(cell_index) != null) {
                        temp.put(testTitle.get(cell_index), row.getCell(cell_index).toString());

                    }
                }
                test_sheet_data.add(temp);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return test_sheet_data;
    }

    public void RunTest(ArrayList<JSONObject> test, JSONObject data, String url) {
        File chromedriver = new File("drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

        File file = new File("test_output.txt");
        FileWriter fw = null;


        // if file doesnt exists, then create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{

        }
        try {
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < test.size(); i++) {
                JSONObject temp_test = test.get(i);
                try {
                    if (temp_test.has("datagroup") && !temp_test.get("datagroup").equals(" ")) {

                        String datagroup = temp_test.get("datagroup").toString();
                        if (data.has(datagroup) && ((JSONArray) data.get(datagroup)).length() > 0) {
                            JSONArray data_object = (JSONArray) data.get(datagroup);

                            for (int k = 0; k < data_object.length(); k++) {
                                openGet(temp_test, new ChromeDriver(), url, data_object.getJSONObject(k), bw);
                            }
                        } else {
                            openGet(temp_test, new ChromeDriver(), url, null, bw);
                        }

                    } else {
                        openGet(temp_test, new ChromeDriver(), url, null, bw);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public static void openGet(JSONObject temp_test, WebDriver driver, String url, JSONObject data, BufferedWriter writer){

        try {
            if(temp_test.has("package") && temp_test.has("operation_class") && !temp_test.get("package").equals(" ") && !temp_test.get("operation_class").equals(" ")) {

                Class cls1 = Class.forName(temp_test.get("package") + "." + temp_test.get("operation_class"));
                Object obj1 = cls1.newInstance();
                Method method = cls1.getDeclaredMethod("get", String.class, String.class, ChromeDriver.class, String.class, JSONObject.class, BufferedWriter.class);
                method.invoke(obj1, temp_test.get("id"), url, driver, temp_test.get("expected_output"), data, writer);

            }
            else{
                System.out.println("No package or class");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
