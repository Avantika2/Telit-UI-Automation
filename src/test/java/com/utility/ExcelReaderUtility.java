package com.utility;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReaderUtility {

    public static Iterator<User> readExcelFile(String filename)  {
        File xlxsfile = new File(System.getProperty("user.dir")+"//TestData//"+filename);
        System.out.println(xlxsfile);
        XSSFWorkbook xssfWorkbook = null;
        XSSFSheet xssfSheet;
        User user;
        List<User> userList = null;
        Row row;
        Cell emailAdsress;
        Cell password;
        Iterator<Row> rowIterator;

        try {
            xssfWorkbook = new XSSFWorkbook(xlxsfile);
            xssfSheet =xssfWorkbook.getSheet("LoginTestData");
            rowIterator = xssfSheet.iterator();
            rowIterator.next();
            userList = new ArrayList<User>();
            while (rowIterator.hasNext()){
                row = rowIterator.next();
                emailAdsress= row.getCell(0);
                password = row.getCell(1);
                user = new User(emailAdsress.toString(),password.toString());
                userList.add(user);
                xssfWorkbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }


    return userList.iterator();

    }

}
