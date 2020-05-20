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

    public static List<WebElement> tryFindElements(By elementName) {
        return getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementName));
    }

    public static WebElement tryFindElement(By elementName) {
        return getFluentWait().until(ExpectedConditions.presenceOfElementLocated(elementName));
    }

    @Step("Open url '{url}'")
    public static String openPage(String url) {
        log.info("Opened page with url '{}'", url);
        WebDriverHolder.getDriver().get(url);
        return WebDriverHolder.getDriver().getCurrentUrl();
    }

    @Step("Open Url {url} in new tab")
    public static void openUrlInNewTab(String url) {
        JavascriptExecutor jse = (JavascriptExecutor) WebDriverHolder.getDriver();
        jse.executeScript("function createDoc(){var w = window.open('" + url + "', '_blank');}; createDoc();");
    }

    @Step("Accept alert")
    public static void acceptBrowserAlert() {
        WebDriverHolder.getDriver().switchTo().alert().accept();
    }

    @Step("Should see element {element}")
    public static void iShouldSee(By element) {
        try {
            getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException ignored) {
            Assert.fail(String.format("Locator %s is not found", element));
        }
    }

    @Step("Element {element} has text {text}")
    public static boolean hasText(By element, String text) {
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
    public static boolean urlContains(String text) {
        log.info("Checking if url contains '{}'", text);
        return WebDriverHolder.getDriver().getCurrentUrl().contains(text);
    }

    @Step("Check is the element {selector} exist")
    public static boolean thisElementExists(By selector, int waitingTime) {
        try {
            getCustomFluentWait(waitingTime).until(ExpectedConditions.visibilityOfElementLocated(selector));
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    @Step("Check alert")
    public static void checkAlert(int timeoutSec) {
        try {
            WebDriverWait wait = new WebDriverWait(WebDriverHolder.getDriver(), timeoutSec);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = WebDriverHolder.getDriver().switchTo().alert();
            alert.accept();
        } catch (Exception err) {
            log.error("Error during checking alert", err);
        }
    }

    @Step("Click on {element}")
    public static void click(By element) {
        tryFindElement(element).click();
    }

    @Step("Fill the element '{element}' with text '{text}'")
    public static void writeText(By element, String text) {
        tryFindElement(element).sendKeys(text);
    }

    @Step("Get text from element '{element}'")
    public static String getText(By element) {
        return tryFindElement(element).getText();
    }

    @Step("Select element '{element}' by value '{value}'")
    public static void selectByValue(By element, String value) {
        new Select(tryFindElement(element)).selectByValue(value);
    }

    @Step("Select element '{element}' by visible text '{text}'")
    public static void selectByVisibleText(By element, String text) {
        new Select(tryFindElement(element)).selectByVisibleText(text);
    }
}
