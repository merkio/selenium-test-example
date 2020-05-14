package com.company.steps;

import com.company.WebDriverHolder;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.List;

@Slf4j
@Component
public class BrowserActions extends BrowserWaiters {

    public BrowserActions(WebDriverHolder driverHolder) {
        super(driverHolder);
    }

    public List<WebElement> tryFindElements(By elementName) {
        return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementName));
    }

    public WebElement tryFindElement(By elementName) {
        return getFluentWait().until(ExpectedConditions.presenceOfElementLocated(elementName));
    }

    @Step("Open url '{url}'")
    public String openPage(String url) {
        log.info("Opened page with url '{}'", url);
        getDriver().get(url);
        return getDriver().getCurrentUrl();

    }

    @Step("Open Url {url} in new tab")
    public BrowserActions openUrlInNewTab(String url) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("function createDoc(){var w = window.open('" + url + "', '_blank');}; createDoc();");
        return this;
    }

    @Step("Accept alert")
    public BrowserActions acceptBrowserAlert() {
        getDriver().switchTo().alert().accept();
        return this;
    }

    @Step("Should see element {element}")
    public BrowserActions iShouldSee(By element) {
        try {
            getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException ignored) {
            Assert.fail(String.format("Locator %s is not found", element));
        }
        return this;
    }

    @Step("Element {element} has text {text}")
    public boolean hasText(By element, String text) {
        log.info("Checking if element has text '{}'", text);
        try {
            waitUntilElementVisible(element);
            return waitUntilTextWillBePresent(element, text);
        } catch (Exception e) {
            log.info("The visible text doesn't match");
            return false;
        }
    }

    @Step("Url contains {text}")
    public boolean urlContains(String text) {
        log.info("Checking if url contains '{}'", text);
        return getDriver().getCurrentUrl().contains(text);
    }

    @Step("Check is the element {selector} exist")
    public Boolean thisElementExists(By selector, int waitingTime) {
        try {
            getCustomFluentWait(waitingTime).until(ExpectedConditions.visibilityOfElementLocated(selector));
            return Boolean.TRUE;
        } catch (TimeoutException ignored) {
            return Boolean.FALSE;
        }
    }

    @Step("Check alert")
    public void checkAlert(int timeoutSec) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), timeoutSec);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception err) {
            log.error("Error during checking alert", err);
        }
    }

    @Step("Click on {element}")
    public void click(By element) {
        tryFindElement(element).click();
    }

    @Step("Fill the element '{element}' with text '{text}'")
    public void writeText(By element, String text) {
        tryFindElement(element).sendKeys(text);
    }

    @Step("Get text from element '{element}'")
    public String getText(By element) {
        return tryFindElement(element).getText();
    }

    @Step("Select element '{element}' by value '{value}'")
    public void selectByValue(By element, String value) {
        new Select(tryFindElement(element)).selectByValue(value);
    }

    @Step("Select element '{element}' by visible text '{text}'")
    public void selectByVisibleText(By element, String text) {
        new Select(tryFindElement(element)).selectByVisibleText(text);
    }

    @Step("Scroll to the element '{element}'")
    public void scrollToElement(By element) {

    }
}
