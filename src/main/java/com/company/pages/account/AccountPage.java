package com.company.pages.account;

import com.company.pages.base.BasePage;
import com.company.pages.category.CategoryPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import static com.company.pages.account.AccountPageLocators.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountPage extends BasePage {

    private final CategoryPage categoryPage;

    @Step("Go to the category with linkText women")
    public CategoryPage goToWomenCategoryPage() {
        iShouldSee(WOMEN_CATEGORY);
        click(WOMEN_CATEGORY);
        return categoryPage;
    }

    @Step("Should see account page for user {fullname}")
    public AccountPage shouldSeeAccountPageForUser(String fullname) {
        iShouldSee(LOGOUT);
        Assert.assertTrue(hasText(HEADING, "MY ACCOUNT"), "Heading doesn't have text 'MY ACCOUNT'");
        Assert.assertTrue(hasText(ACCOUNT, fullname), "Account doesn't have text " + fullname);
        Assert.assertTrue(hasText(INFO_ACCOUNT, "Welcome to your account."),
                          "Info account doesn't have text 'Welcome to your account'");
        return this;
    }
}
