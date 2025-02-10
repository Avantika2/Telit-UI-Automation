package com.ui.tests;

import com.ui.pages.HomePage;
import static org.testng.Assert.*;

import com.ui.pojo.User;

import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.constants.Browser.CHROME;
import static com.constants.Browser.CHROME;

@Listeners(com.ui.listners.TestListners.class)

public class LoginTest extends TestBase{

    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Test(description = "Verify with the valid user if user is able to login", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.providers.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void loginTest(User user){
        //Login test with JSON data
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "John Doe");
    }

    @Test(description = "Verify with the valid user if user is able to login", groups = {"e2e","sanity"},
            dataProviderClass = com.ui.providers.LoginDataProvider.class, dataProvider = "loginTestCSVDataProvider")
    public void loginCSVTest(User user){
        //Login test with CSV data
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "John Doe");

    }

//    @Test(description = "Verify with the valid user if user is able to login", groups = {"e2e","sanity"},
//            dataProviderClass = com.ui.providers.LoginDataProvider.class,
//            dataProvider = "loginTestExcelDataProvider")
//    public void loginExcelTest(User user){
//
//
//        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(),
//                user.getPassword()).getUserName(), "John Doe");
//
//    }
}
