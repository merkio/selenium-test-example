package com.company.pages.checkout;

import com.company.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.company.pages.checkout.CheckoutPageLocators.*;

@Slf4j
@Component
public class CheckoutPage extends BasePage {

    @Step("Proceed checkout")
    public CheckoutPage proceedCheckout() {
        click(CART_PROCEED_CHECKOUT);
        click(CART_NAVIGATION_CHECKOUT);
        click(PROCESS_ADDRESS);
        click(UNIFORM_CGV);
        click(PROCESS_CARRIER);
        click(BANK_WIRE);
        click(CART_NAVIGATION_BUTTON);
        return this;
    }

    @Step("Should see confirmation order")
    public void shouldSeeConfirmationOrder() {
        hasText(HEADING, "ORDER CONFIRMATION");
        iShouldSee(STEP_DONE);
        iShouldSee(STEP_END);
        hasText(CHEQUE_INDENT, "Your order on My Store is complete.");
    }
}
