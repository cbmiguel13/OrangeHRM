package com.automation.framework.orangehrm.driver;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.automation.framework.orangehrm.utils.ReadProperties;

public class DriverManager {

	private WebDriver driver;

	// Inicializar el driver
	public void initDriver(boolean useBrowserStack) {

		try {
			if (useBrowserStack) {
				initBrowserStackDriver();
			} else {
				initLocalDriver();
			}

			driver.manage().window().maximize();

		} catch (Exception e) {
			  System.out.println("Error al inicializar el driver [" + e.getMessage() + "]");
		}

	}

	// Inicializar el driver local
	private void initLocalDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
	    if (Boolean.parseBoolean(System.getenv("HEADLESS"))) {
	        options.addArguments("--headless");
	        options.addArguments("--no-sandbox");
	        options.addArguments("--disable-dev-shm-usage");
	    }
		
		driver = new ChromeDriver();
	}

	private void initBrowserStackDriver() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

        HashMap<String, Object> browserstackOptions = new HashMap<>();
        browserstackOptions.put("os", "Windows");
        browserstackOptions.put("osVersion", "11");
        browserstackOptions.put("browserVersion", "latest");
        browserstackOptions.put("projectName", "OrangeHRM");
        browserstackOptions.put("buildName", "Build 1.0");
        browserstackOptions.put("sessionName", "Flujo Completo");

        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", " latest");
        caps.setCapability("bstack:options", browserstackOptions);

        String bsUrl = "https://" 
            + ReadProperties.get("bs.username") 
            + ":" 
            + ReadProperties.get("bs.accesskey") 
            + "@hub.browserstack.com/wd/hub";

        driver = new RemoteWebDriver(new URL(bsUrl), caps);
		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
	}

	// Retornar el driver
	public WebDriver getDriver() {
		return driver;
	}

	// Cerrar y quitar el driver
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}
}