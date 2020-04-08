package com.company.challenge.pages.login;

import com.company.challenge.pages.account.AccountPage;
import com.company.challenge.pages.base.BasePage;
import com.company.challenge.pages.signup.SignUpPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement email;

    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLogin;

    @FindBy(id = "email_create")
    private WebElement emailCreate;

    @FindBy(id = "SubmitCreate")
    private WebElement submitCreate;

    @Step("Login default user")
    public AccountPage loginAsDefaultUser() {
        logger.info("Login as default user");
        wait.until(ExpectedConditions.visibilityOf(email));
        writeText(email, properties.userEmail());
        writeText(password, properties.userPassword());
        click(submitLogin);
        return getPage(AccountPage.class);
    }

    @Step("Go to sign up page, with new user email {0}")
    public SignUpPage createNewUserWithEmail(String email) {
        logger.info(String.format("Create new user with email: %s", email));
        wait.until(ExpectedConditions.visibilityOf(emailCreate));
        writeText(emailCreate, email);
        click(submitCreate);
        return getPage(SignUpPage.class);
    }
}
