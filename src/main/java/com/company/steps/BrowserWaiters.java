package com.company.steps;

import com.company.WebDriverHolder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

import static com.company.config.BrowserProperties.BROWSER_PROPERTIES;
import static org.openqa.selenium.By.cssSelector;

@Slf4j
@Component
public class BrowserWaiters {

    public static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(WebDriverHolder.getDriver())
            .withTimeout(Duration.ofSeconds(BROWSER_PROPERTIES.fluentWaitInterval()))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);
    }

    public static FluentWait<WebDriver> getCustomFluentWait(Integer waitingTime) {
        return new FluentWait<>(WebDriverHolder.getDriver())
            .withTimeout(Duration.ofSeconds(waitingTime))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
    }

    public static Boolean waitUntilElementIsActive(String element) {
        return getFluentWait().until(ExpectedConditions.elementToBeSelected(cssSelector(element)));
    }


    public static Boolean waitUntilAttributeContains(By by, String attribute, String value) {
        return getFluentWait().until(ExpectedConditions.attributeContains(by, attribute, value));
    }

    public static List<WebElement> waitUntilAllElementsPresent(By by) {
        return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public static Boolean waitUntilValueWillBePresentInElement(String selector, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(cssSelector(selector), text));
    }

    public static Boolean waitUntilValueWillBePresentInElement(WebElement element, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public static Boolean waitUntilValueWillBePresentInElement(By element, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    public static Boolean waitUntilTextWillBePresent(By by, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    public static WebElement waitUntilElementVisible(By element) {
        return getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
