package com.company.pages.category;

import com.company.WebDriverHolder;
import com.company.pages.base.BasePage;
import com.company.pages.checkout.CheckoutPage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.company.pages.category.CategoryPageLocators.SUBMIT;
import static com.company.pages.category.CategoryPageLocators.getByForElementWithTitle;

@Slf4j
@Component
public class CategoryPage extends BasePage {

    private final CheckoutPage checkoutPage;

    public CategoryPage(WebDriverHolder driverHolder, CheckoutPage checkoutPage) {
        super(driverHolder);
        this.checkoutPage = checkoutPage;
    }

    @Step("Click to the element of category with title: {title}")
    public CategoryPage clickOnElementWithTitle(String title) {
        log.info("Click on the element with title '{}'", title);
        getDriver().findElement(getByForElementWithTitle(title)).click();
        return this;
    }

    @Step("Submit Order")
    public CheckoutPage submitTheOrder() {
        log.info("Submit the order");
        click(SUBMIT);
        return checkoutPage;
    }
}
