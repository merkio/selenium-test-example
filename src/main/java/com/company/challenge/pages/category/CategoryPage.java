package com.company.challenge.pages.category;

import com.company.challenge.pages.base.BasePage;
import com.company.challenge.pages.checkout.CheckoutPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Setter
@Getter
public class CategoryPage extends BasePage {

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "Submit")
    private WebElement submit;

    @Step("Click to the element of category with title: {}")
    public CategoryPage clickOnElementWithTitle(String title) {
        click(driver.findElement(By.xpath(String.format("//a[@title='%s']/ancestor::li", title))));
        return this;
    }

    @Step("Submit")
    public CheckoutPage submitTheOrder() {
        logger.info("Submit order");
        wait.until(ExpectedConditions.visibilityOf(submit));
        click(submit);
        return getPage(CheckoutPage.class);
    }
}
