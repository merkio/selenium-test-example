package com.company.base;

import com.company.WebDriverHolder;
import com.company.listeners.FailureTestListener;
import com.company.pages.category.CategoryPage;
import com.company.pages.checkout.CheckoutPage;
import com.company.pages.home.HomePage;
import com.company.pages.login.LoginPage;
import com.company.pages.signup.SignUpPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Slf4j
@Listeners({FailureTestListener.class})
public class BaseWebTest extends BaseSpringTest {

    @Lazy
    @Autowired
    private HomePage homePage;

    @Lazy
    @Autowired
    private LoginPage loginPage;

    @Lazy
    @Autowired
    private SignUpPage signUpPage;

    @Lazy
    @Autowired
    private CheckoutPage checkoutPage;

    @Lazy
    @Autowired
    private CategoryPage categoryPage;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser(@Optional("chrome") String browser, Method method) {
        WebDriverHolder.setDriver(browser, method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method method, ITestResult testResult) {
        WebDriverHolder.tearDownBrowser();
    }
}
