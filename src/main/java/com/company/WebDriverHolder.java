package com.company;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

@Slf4j
public class WebDriverHolder {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static void setDriver(String browserName, String testName) {
        log.info("Starting browser '{}' for test '{}'", browserName, testName);

        WebDriver driver = BrowserFactory.getBrowser(browserName, testName);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.MINUTES);
        driver.manage().window().maximize();
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void tearDownBrowser() {
        log.info("Closing Browser..");
        if (getDriver() != null) {
            try {
                getDriver().quit();
            } catch (Exception e) {
                log.warn("Failed to close browser", e);
            }
        }
    }
}
