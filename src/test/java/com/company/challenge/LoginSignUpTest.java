package com.company.challenge;

import com.company.challenge.pages.home.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

@DisplayName("Login & SignUp Tests")
public class LoginSignUpTest extends BaseTest {

    @Test
    @DisplayName("SignUp")
    void signUpTest() {
        HomePage homePage = getPage(HomePage.class);

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

    @Test
    @DisplayName("Login")
    void logInTest() {
        HomePage homePage = getPage(HomePage.class);

        String fullName = "Joe Black";
        homePage
            .goToHomePage()
            .goToLoginPage()
            .loginAsDefaultUser()
            .shouldSeeAccountPageForUser(fullName);
    }
}
