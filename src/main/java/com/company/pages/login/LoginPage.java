package com.company.pages.login;

import com.company.config.TestProperties;
import com.company.pages.account.AccountPage;
import com.company.pages.base.BasePage;
import com.company.pages.signup.SignUpPage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.company.pages.login.LoginPageLocators.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginPage extends BasePage {

    private final TestProperties testProperties;
    private final AccountPage accountPage;
    private final SignUpPage signUpPage;

    @Step("Login as default user")
    public AccountPage loginAsDefaultUser() {
        log.info("Login as default user");
        iShouldSee(EMAIL);
        writeText(EMAIL, testProperties.getUserEmailDefault());
        writeText(PASSWORD, testProperties.getUserPasswordDefault());
        click(SUBMIT_LOGIN);
        return accountPage;
    }


    @Step("Go to sign up page, with new user email {0}")
    public SignUpPage createNewUserWithEmail(String email) {
        log.info("Create new user with email: {}", email);
        iShouldSee(EMAIL_CREATE);
        writeText(EMAIL_CREATE, email);
        click(SUBMIT_CREATE);
        return signUpPage;
    }
}
