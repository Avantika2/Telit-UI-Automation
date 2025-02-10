package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;

import com.constants.Env;
import com.utility.BrowserUtility;
import static com.utility.PropertiesUtil.*;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class HomePage extends BrowserUtility {

    Logger logger = LoggerUtility.getLogger(this.getClass());
    public HomePage(Browser browserName, boolean isHeadless) {
        super(browserName,isHeadless);
        //GoToWebsite(PropertiesUtil.readProperty(QA,"URL"));
        GoToWebsite(JSONUtility.readJSON(QA).getUrl());
        logger.info("Luanching the browser from the Browser class");
    }
    //Page Object Design Pattern
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(@class,\"login\")]");

    public  LoginPage goToLoginPage(){
        clickOn(SIGN_IN_LINK_LOCATOR);
        String url = readProperty(QA, "URL");
        LoginPage loginPage = new LoginPage(getDriver());
        logger.info("Going to SIGN IN page");
        return loginPage;

    }
}
