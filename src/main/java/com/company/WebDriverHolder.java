package com.company;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebDriverHolder {

    private final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private final BrowserFactory factory;

    public void setDriver(String browserName, String testName) {
        log.info("Starting browser '{}'", browserName);

        WebDriver driver = factory.getBrowser(browserName, testName);
        driver.manage().timeouts().setScriptTimeout(3, TimeUnit.MINUTES);
        driver.manage().window().maximize();
        webDriver.set(driver);
    }

    public WebDriver getDriver() {
        return webDriver.get();
    }

    public void tearDownBrowser() {
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
