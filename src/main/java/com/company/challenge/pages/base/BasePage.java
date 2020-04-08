package com.company.challenge.pages.base;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Setter
@Getter
public abstract class BasePage extends PageFactory {

    public BasePage(WebDriver driver) {
        super(driver);
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
