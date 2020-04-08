package com.company.challenge.pages.account;

import com.company.challenge.pages.base.BasePage;
import com.company.challenge.pages.category.CategoryPage;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.Setter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter
@Setter
public class AccountPage extends BasePage {

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    private WebElement heading;

    @FindBy(className = "account")
    private WebElement account;

    @FindBy(className = "info-account")
    private WebElement infoAccount;

    @FindBy(className = "logout")
    private WebElement logout;

    @FindBy(linkText = "Women")
    private WebElement womenCategory;

    @Step("Go to the category with linkText women")
    public CategoryPage goToWomenCategoryPage() {
        wait.until(ExpectedConditions.visibilityOf(womenCategory));
        click(womenCategory);
        return getPage(CategoryPage.class);
    }

    @Step("Should see account page")
    public AccountPage shouldSeeAccountPageForUser(String fullname) {
        wait.until(ExpectedConditions.visibilityOf(logout));
        Assert.assertThat("Wrong heading", getText(heading), Matchers.is("MY ACCOUNT"));
        Assert.assertThat("Wrong account text", getText(account), Matchers.is(fullname));
        Assert.assertThat("Wrong info-account text", getText(infoAccount),
                          Matchers.containsString("Welcome to your account."));
        Assert.assertThat("URL doesn't contain param controller",
                          driver.getCurrentUrl(), Matchers.containsString("controller=my-account"));
        return this;
    }
}
