package com.company.steps;

import com.company.WebDriverHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;

@Slf4j
@Component
@RequiredArgsConstructor
public class BrowserWaiters {

    protected final WebDriverHolder driverHolder;

    @Value("${browser.fluent_wait:60}")
    private Integer fluentWaitInterval;

    protected FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(getDriver())
            .withTimeout(Duration.ofSeconds(fluentWaitInterval))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);
    }

    protected FluentWait<WebDriver> getCustomFluentWait(Integer waitingTime) {
        return new FluentWait<>(getDriver())
            .withTimeout(Duration.ofSeconds(waitingTime))
            .pollingEvery(Duration.ofSeconds(1))
            .ignoring(NoSuchElementException.class);
    }

    protected Boolean waitUntilElementIsActive(String element) {
        return getFluentWait().until(ExpectedConditions.elementToBeSelected(cssSelector(element)));
    }


    protected Boolean waitUntilAttributeContains(By by, String attribute, String value) {
        return getFluentWait().until(ExpectedConditions.attributeContains(by, attribute, value));
    }

    protected List<WebElement> waitUntilAllElementsPresent(By by) {
        return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected Boolean waitUntilValueWillBePresentInElement(String selector, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(cssSelector(selector), text));
    }

    protected Boolean waitUntilValueWillBePresentInElement(WebElement element, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    protected Boolean waitUntilValueWillBePresentInElement(By element, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    protected Boolean waitUntilTextWillBePresent(By by, String text) {
        return getFluentWait().until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }

    protected WebElement waitUntilElementVisible(By element) {
        return getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    protected WebDriver getDriver() {
        return driverHolder.getDriver();
    }
}
