package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BrowserUtility {

    private static final By EMAIL_TEXT_LOCATOR = By.id("email");
    private static final By PASSWORD_TEXT_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_BUTTON_LOCATOR = By.id("SubmitLogin");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MyAccountPage doLoginWith(String emailAddress, String Password){
        enterText(EMAIL_TEXT_LOCATOR,emailAddress);
        enterText(PASSWORD_TEXT_LOCATOR,Password);
        clickOn(SUBMIT_LOGIN_BUTTON_LOCATOR);
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        return myAccountPage;
    }
}
