package com.company.pages.checkout;

import org.openqa.selenium.By;

public final class CheckoutPageLocators {

    /*
    *  @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
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
    *
    *  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText().contains("Your order on My Store is complete."));
    * */

    public final static By HEADING = By.tagName("h1");
    public final static By CART_PROCEED_CHECKOUT = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
    public final static By CART_NAVIGATION_CHECKOUT = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
    public final static By PROCESS_ADDRESS = By.name("processAddress");
    public final static By UNIFORM_CGV = By.id("uniform-cgv");
    public final static By PROCESS_CARRIER = By.name("processCarrier");
    public final static By BANK_WIRE = By.className("bankwire");
    public final static By CART_NAVIGATION_BUTTON = By.xpath("//*[@id='cart_navigation']/button");
    public final static By STEP_DONE = By.xpath("//li[@class='step_done step_done_last four']");
    public final static By STEP_END = By.xpath("//li[@id='step_end' and @class='step_current last']");
    public final static By CHEQUE_INDENT = By.xpath("//*[@class='cheque-indent']/strong");

}
