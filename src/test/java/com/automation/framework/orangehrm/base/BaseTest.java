package com.automation.framework.orangehrm.base;

import org.testng.annotations.*;
import com.automation.framework.orangehrm.driver.DriverManager;
import com.automation.framework.orangehrm.utils.ReadProperties;
import com.automation.framework.orangehrm.utils.ReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected DriverManager driverManager = new DriverManager();
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeClass
    public void setUp() {
    	extent = ReportManager.getInstance();
    	driverManager.initDriver(Boolean.valueOf(ReadProperties.get("useBrowserStack")));
        driver = driverManager.getDriver();
        driver.get(ReadProperties.get("url"));
    }

    @AfterClass
    public void tearDown() {
    	driverManager.quitDriver();
    	extent.flush();
    }
}
