package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public abstract class BrowserUtility {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public WebDriver getDriver() {

        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        //Default constructor
        this.driver.set(driver);
    }

    Logger logger = LoggerUtility.getLogger(this.getClass());
    public BrowserUtility(String browserName){
        //Constructor with String browserName
        if(browserName.equalsIgnoreCase("chrome")){
            driver.set(new ChromeDriver());
          logger.info("Intialiazing the chrome driver");
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
            logger.info("Intialiazing the edge driver");
        }
        else{
            System.err.println("Invalid Browser Name. Please select chrome or edge only");
            logger.error("Invalid browser name passed");
        }
    }


    public BrowserUtility(Browser browserName){
        //Constructor with Browser enum parameter
        if(browserName == Browser.CHROME){
            driver.set(new ChromeDriver());
        }
        else if (browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless){
        //Parameterized constructor
        if(browserName == Browser.CHROME ) {
            if (isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            } else {
                driver.set(new ChromeDriver());
            }
        }
        else if (browserName == Browser.EDGE) {
            if (isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old");
                options.addArguments("--window-size=1920,1080");
                driver.set(new EdgeDriver(options));
            } else {
                driver.set(new EdgeDriver());
            }
        }
    }





    public void GoToWebsite(String url){
        driver.get().get(url);
        logger.info("Going to the website");

    }

    public void maximizeWindow(){
        driver.get().manage().window().maximize();
        logger.info("Maximizing the window");
    }

    public void clickOn(By locator){
        WebElement signInLinkWebElement = driver.get().findElement(locator);
        signInLinkWebElement.click();
        logger.info("Clicking on Sign in element");
    }

    public void enterText(By locator, String textToEnter){
        WebElement emailElement = driver.get().findElement(locator);
        emailElement.sendKeys(textToEnter);
        logger.info("Entering the email id");
    }

    public String getVisibleText(By locator){
        WebElement element = driver.get().findElement(locator);
        logger.info("Returning the text of locator");
        return element.getText();

    }

    public String takeScreensShot(String name)  {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screensShotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat  format = new SimpleDateFormat("HH:mm:ss");
        String timestamp = format.format(date);
        String path = System.getProperty("user.dir")+"//scrrenshots"+name+" - "+timestamp+".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screensShotData,screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
