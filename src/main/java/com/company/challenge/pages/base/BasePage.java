package com.company.challenge.pages.base;

import com.company.challenge.TestProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Setter
@Getter
public abstract class BasePage {

    protected static TestProperties properties = TestProperties.PROPERTIES;

    protected Logger logger;
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void writeText(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
       return element.getText();
    }

    protected void selectByValue(WebElement element, String value) {
        new Select(element).selectByValue(value);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }
}
