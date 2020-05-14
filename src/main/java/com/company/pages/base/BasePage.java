package com.company.pages.base;

import com.company.WebDriverHolder;
import com.company.steps.BrowserActions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class BasePage extends BrowserActions {

    public BasePage(WebDriverHolder driverHolder) {
        super(driverHolder);
    }
}
