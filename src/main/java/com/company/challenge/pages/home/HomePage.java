package com.company.challenge.pages.home;

import com.company.challenge.pages.base.BasePage;
import com.company.challenge.pages.login.LoginPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "login")
    private WebElement login;

    @Step("Go to home page")
    public HomePage goToHomePage() {
        driver.get(properties.baseUrl());
        return this;
    }

    @Step("Go to login page")
    public LoginPage goToLoginPage() {
        wait.until(ExpectedConditions.visibilityOf(login));
        click(login);
        return getPage(LoginPage.class);
    }
}
