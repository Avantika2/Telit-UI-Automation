package com.ui.tests;

import com.beust.jcommander.Parameter;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    private boolean isHeadless ;

    Logger logger = LoggerUtility.getLogger(this.getClass());
    @Parameters({"isHeadless"})
    @BeforeMethod(description = "Load the HomePage of the website")
    public void setup(
            @Optional("true") boolean isHeadless){
        //Intial setup before login
        homePage = new HomePage(CHROME,isHeadless);
    }

    public BrowserUtility getInstance(){
        return homePage;
    }
}
