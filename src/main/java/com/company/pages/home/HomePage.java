package com.company.pages.home;

import com.company.config.TestProperties;
import com.company.pages.base.BasePage;
import com.company.pages.login.LoginPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.company.pages.home.HomePageLocators.LOGIN;

@Slf4j
@Component
@RequiredArgsConstructor
public class HomePage extends BasePage {

    private final TestProperties testProperties;
    private final LoginPage loginPage;

    @Step("Go to home page")
    public HomePage goToHomePage() {
        log.info("Go to home page '{}'", testProperties.getBaseUrl());
        openPage(testProperties.getBaseUrl());
        return this;
    }

    @Step("Go to login page")
    public LoginPage goToLoginPage() {
        iShouldSee(LOGIN);
        click(LOGIN);
        return loginPage;
    }
}
