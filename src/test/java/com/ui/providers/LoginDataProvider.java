package com.ui.providers;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;
import com.utility.ExcelReaderUtility;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginDataProvider {
     @DataProvider(name = "LoginTestDataProvider")
    public Iterator<Object[]> loginDataProvider() throws FileNotFoundException {
        Gson gson = new Gson();
        File testDataFile  = new File(System.getProperty("user.dir")+"\\TestData\\LoginData.json");
         System.out.println("Excel file location = "+testDataFile);
        FileReader fileReader = new FileReader(testDataFile);
        TestData data = gson.fromJson(fileReader, TestData.class);
        List<Object[]> dataReturn = new ArrayList<Object[]>();
        for(User user : data.getData()){
            dataReturn.add(new Object[]{user});
        }
        return dataReturn.iterator();
    }

    @DataProvider(name = "loginTestCSVDataProvider")
    public Iterator<User> loginCSVDataProvider(){
        return CSVReaderUtility.readCSVFile("LoginData.csv");
    }

    @DataProvider(name = "loginTestExcelDataProvider")
    public Iterator<User> loginExcelDataProvider(){
        return ExcelReaderUtility.readExcelFile("LoginData.xlsx");
    }
}
