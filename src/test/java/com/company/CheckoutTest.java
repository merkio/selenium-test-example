package com.company;

import com.company.base.BaseWebTest;
import com.company.pages.home.HomePage;
import io.qameta.allure.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

@Feature("Checkout Test")
public class CheckoutTest extends BaseWebTest {

    @Autowired
    private HomePage homePage;

    @Test(description = "Checkout with default user")
    void checkoutTest() {
        String fullName = "Joe Black";
        homePage.goToHomePage()
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
