package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BrowserUtility {

    private static final By User_NAME_ACCOUNT = By.xpath("//a[contains(@title,\"View my customer account\")]");

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        return getVisibleText(User_NAME_ACCOUNT);
    }
}
