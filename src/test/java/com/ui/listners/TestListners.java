package com.ui.listners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExcelReaderUtility;
import com.utility.ExtentReportUtility;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListners  implements ITestListener {

    Logger logger = LoggerUtility.getLogger(this.getClass());

    ExtentSparkReporter extentSparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite Started");
        ExtentReportUtility.setupSparkReporter("report.html");

    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        ExtentReportUtility.createExtentTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " "+"PASSED!");
        ExtentReportUtility.getTest().log(Status.PASS,result.getMethod().getMethodName() + " "+"PASSED!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " "+"FAILED!");
        logger.info(result.getThrowable().getMessage());
        ExtentReportUtility.getTest().log(Status.FAIL,result.getMethod().getMethodName() + " "+"FAILED!");
        ExtentReportUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
        Object testclass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase)testclass).getInstance();
        logger.info("Capturing the screenshot of failed test cases");
        String screenshotpath = browserUtility.takeScreensShot(result.getMethod().getMethodName());
        logger.info("Attaching the scrrenshot to the HTML file");
        ExtentReportUtility.getTest().addScreenCaptureFromPath(screenshotpath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " "+"SKIPPED!");
        ExtentReportUtility.getTest().log(Status.SKIP,result.getMethod().getMethodName() + " "+"SKIPPEED!");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite Completed");
        ExtentReportUtility.flushReport();
    }
}
