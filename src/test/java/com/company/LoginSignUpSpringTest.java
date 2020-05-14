package com.company;

import com.company.base.BaseWebTest;
import com.company.pages.home.HomePage;
import io.qameta.allure.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Date;

@Feature("Login & SignUp Tests")
public class LoginSignUpSpringTest extends BaseWebTest {

    @Autowired
    private HomePage homePage;

    @Test(description = "SignUp")
    void signUpTest() {

        String timestamp = String.valueOf(new Date().getTime());
        String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
        String fullname = "Firstname Lastname";

        homePage
            .goToHomePage()
            .goToLoginPage()
            .createNewUserWithEmail(email)
            .signUp()
            .shouldSeeAccountPageForUser(fullname);
    }

    @Test(description = "Login with default user")
    void logInTest() {
        String fullName = "Joe Black";
        homePage
            .goToHomePage()
            .goToLoginPage()
            .loginAsDefaultUser()
            .shouldSeeAccountPageForUser(fullName);
    }
}
