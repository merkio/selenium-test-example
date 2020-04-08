package com.company.challenge.pages.base;

import com.company.challenge.TestProperties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {
    protected static TestProperties properties = TestProperties.PROPERTIES;

    protected WebDriver driver;
    protected Logger logger;
    protected WebDriverWait wait;

    public PageFactory(WebDriver driver, Logger logger, WebDriverWait wait) {
        this.driver = driver;
        this.logger = logger;
        this.wait = wait;
    }

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public <T extends BasePage> T getPage(Class<T> pageClass) {
        T page = org.openqa.selenium.support.PageFactory.initElements(driver, pageClass);
        page.logger = logger;
        page.wait = wait;
        return page;
    }
}
