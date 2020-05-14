package com.company.pages.category;

import org.openqa.selenium.By;

public final class CategoryPageLocators {

    public final static By SUBMIT = By.name("Submit");

    public static By getByForElementWithTitle(String title) {
        return By.xpath(String.format("//a[@title='%s']/ancestor::li", title));
    }
}
