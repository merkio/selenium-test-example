package com.company.pages.category;

import com.company.pages.base.BasePage;
import com.company.pages.checkout.CheckoutPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.company.pages.category.CategoryPageLocators.SUBMIT;
import static com.company.pages.category.CategoryPageLocators.getByForElementWithTitle;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryPage extends BasePage {

    private final CheckoutPage checkoutPage;

    @Step("Click to the element of category with title: {title}")
    public CategoryPage clickOnElementWithTitle(String title) {
        log.info("Click on the element with title '{}'", title);
        click(getByForElementWithTitle(title));
        return this;
    }

    @Step("Submit Order")
    public CheckoutPage submitTheOrder() {
        log.info("Submit the order");
        click(SUBMIT);
        return checkoutPage;
    }
}
