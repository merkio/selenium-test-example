package com.company.challenge;

import com.company.challenge.pages.home.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Checkout Tests")
public class CheckoutTest extends BaseTest {

    @Test
    @DisplayName("Checkout")
    void checkoutTest() {
        HomePage homePage = getPage(HomePage.class);

        String fullName = "Joe Black";
        homePage
            .goToHomePage()
            .goToLoginPage()
            .loginAsDefaultUser()
            .shouldSeeAccountPageForUser(fullName)
            .goToWomenCategoryPage()
            .clickOnElementWithTitle("Faded Short Sleeve T-shirts")
            .submitTheOrder()
            .proceedCheckout()
            .shouldSeeConfirmationOrder();
    }
}
