package com.company.challenge.pages.signup;

import com.company.challenge.pages.account.AccountPage;
import com.company.challenge.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customer_firstname")
    private WebElement customerFirstname;

    @FindBy(id = "customer_lastname")
    private WebElement customerLastname;

    @FindBy(id = "passwd")
    private WebElement passwd;

    @FindBy(id = "days")
    private WebElement days;

    @FindBy(id = "months")
    private WebElement months;

    @FindBy(id = "years")
    private WebElement years;

    @FindBy(id = "company")
    private WebElement company;

    @FindBy(id = "address1")
    private WebElement address1;

    @FindBy(id = "address2")
    private WebElement address2;

    @FindBy(id = "city")
    private WebElement  city;

    @FindBy(id = "id_state")
    private WebElement stateId;

    @FindBy(id = "postcode")
    private WebElement postcode;

    @FindBy(id = "other")
    private WebElement other;

    @FindBy(id = "phone")
    private WebElement phone;

    @FindBy(id = "phone_mobile")
    private WebElement phoneMobile;

    @FindBy(id = "alias")
    private WebElement alias;

    @FindBy(id = "submitAccount")
    private WebElement submitAccount;

    @Step("Sign up new user")
    public AccountPage signUp() {
        return signUp(new HashMap<>());
    }

    @Step("Sign up custom new user with params: {0}")
    public AccountPage signUp(Map<String, String> values) {
        String name = values.getOrDefault("name", "Firstname");
        String surname = values.getOrDefault("surname", "Lastname");
        String password = values.getOrDefault("password", "Qwerty");
        String daysValue = values.getOrDefault("days", "1");
        String monthsValue = values.getOrDefault("months", "1");
        String yearsValue = values.getOrDefault("years", "2000");
        String companyValue = values.getOrDefault("company", "Company");
        String address1Value = values.getOrDefault("address1", "Qwerty, 123");
        String address2Value = values.getOrDefault("address2", "zxcvb");
        String cityValue = values.getOrDefault("city", "Qwerty");
        String stateIdValue = values.getOrDefault("stateId", "Colorado");
        String postcodeValue = values.getOrDefault("postcode", "12345");
        String otherValue = values.getOrDefault("other", "Qwerty");
        String phoneValue = values.getOrDefault("phone", "12345123123");
        String phoneMobileValue = values.getOrDefault("phoneMobile", "12345123123");
        String aliasValue = values.getOrDefault("alias", "hf");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();

        logger.info("Fulfilling the signup form with params");
        writeText(customerFirstname, name);
        writeText(customerLastname, surname);
        writeText(passwd, password);
        selectByValue(days, daysValue);
        selectByValue(months, monthsValue);
        selectByValue(years, yearsValue);
        writeText(company, companyValue);
        writeText(address1, address1Value);
        writeText(address2, address2Value);
        writeText(city, cityValue);
        selectByVisibleText(stateId, stateIdValue);
        writeText(postcode, postcodeValue);
        writeText(other, otherValue);
        writeText(phone, phoneValue);
        writeText(phoneMobile, phoneMobileValue);
        writeText(alias, aliasValue);

        click(submitAccount);

        return getPage(AccountPage.class);
    }
}
