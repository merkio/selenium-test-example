package com.company.challenge.pages.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactory {

    protected WebDriver driver;
    protected Logger logger;
    private WebDriverWait wait;

    public PageFactory(WebDriver driver, Logger logger, WebDriverWait wait) {
        this.driver = driver;
        this.logger = logger;
        this.wait = wait;
    }

    public <T extends BasePage> T getPage(Class<T> pageClass) {
        T page = org.openqa.selenium.support.PageFactory.initElements(driver, pageClass);
        page.setLogger(logger);
        page.setWait(wait);
        return page;
    }
}
