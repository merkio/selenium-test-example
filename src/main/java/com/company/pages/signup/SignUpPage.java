package com.company.pages.signup;

import com.company.pages.account.AccountPage;
import com.company.pages.base.BasePage;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.company.pages.signup.SignUpPageLocators.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class SignUpPage extends BasePage {

    private final AccountPage accountPage;

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

        click(GENDER2);

        log.info("Fulfilling the signup form with params");
        writeText(CUSTOMER_FIRSTNAME, name);
        writeText(CUSTOMER_LASTNAME, surname);
        writeText(PASSWORD, password);
        selectByValue(DAYS, daysValue);
        selectByValue(MONTHS, monthsValue);
        selectByValue(YEARS, yearsValue);
        writeText(COMPANY, companyValue);
        writeText(ADDRESS1, address1Value);
        writeText(ADDRESS2, address2Value);
        writeText(CITY, cityValue);
        selectByVisibleText(ID_STATE, stateIdValue);
        writeText(POSTCODE, postcodeValue);
        writeText(OTHER, otherValue);
        writeText(PHONE, phoneValue);
        writeText(PHONE_MOBILE, phoneMobileValue);
        writeText(ALIAS, aliasValue);

        click(SUBMIT_ACCOUNT);
        return accountPage;
    }
}
