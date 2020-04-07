package com.company.challenge.pages.checkout;

import com.company.challenge.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Setter
@Getter
public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
    private WebElement cartProceedCheckout;

    @FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    private WebElement cartNavigationCheckout;

    @FindBy(name = "processAddress")
    private WebElement processAddress;

    @FindBy(id = "uniform-cgv")
    private WebElement uniformCgv;

    @FindBy(name = "processCarrier")
    private WebElement processCarrier;

    @FindBy(className = "bankwire")
    private WebElement bankWire;

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement cartNavigationButton;

    @FindBy(css = "h1")
    private WebElement heading;

    @Step("Proceed checkout")
    public CheckoutPage proceedCheckout() {
        wait.until(ExpectedConditions.visibilityOf(cartProceedCheckout)).click();
        wait.until(ExpectedConditions.visibilityOf(cartNavigationCheckout)).click();
        wait.until(ExpectedConditions.visibilityOf(processAddress)).click();
        wait.until(ExpectedConditions.visibilityOf(uniformCgv)).click();
        wait.until(ExpectedConditions.visibilityOf(processCarrier)).click();
        wait.until(ExpectedConditions.visibilityOf(bankWire)).click();
        wait.until(ExpectedConditions.visibilityOf(cartNavigationButton)).click();
        return this;
    }

    @Step("Should see confirmation order")
    public void shouldSeeConfirmationOrder() {
        wait.until(ExpectedConditions.visibilityOf(heading));

        Assert.assertEquals("ORDER CONFIRMATION", heading.getText());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
        Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
    }
}
