package com.company.listeners;

import com.company.WebDriverHolder;
import com.company.steps.BrowserActions;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class FailureTestListener implements ITestListener {

    private final WebDriverHolder webDriverHolder;
    private final BrowserActions browserActions;

    @Override
    public void onTestFailure(ITestResult result) {
        uploadScreenshots(result);
        analyzeLog(result.getMethod().getMethodName());
    }

    private void uploadScreenshots(ITestResult testResult) {
        if (Objects.nonNull(webDriverHolder.getDriver())) {
            try {
                browserActions.checkAlert(0);

                File scrFile = ((TakesScreenshot) webDriverHolder.getDriver()).getScreenshotAs(OutputType.FILE);
                attachBrowserScreenshot(scrFile);
            } catch (Exception e) {
                log.error("Failed to attach screenshot to test [{}]", testResult.getName(), e);
            }
        }
    }

    @Attachment(value = "Browser window screenshot", type = "image/png")
    private static byte[] attachBrowserScreenshot(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }

    @Step("Adding collected logs")
    private void analyzeLog(String methodName) {
        if (Objects.nonNull(webDriverHolder.getDriver())) {
            browserActions.checkAlert(0);

            log.info("Last URL was: ${webDriverHolder.getDriver().getCurrentUrl()}");

            if (((RemoteWebDriver) webDriverHolder.getDriver()).getCapabilities().getBrowserName().equals("chrome")) {
                LogEntries logEntries = webDriverHolder.getDriver().manage().logs().get(LogType.BROWSER);
                for (LogEntry entry : logEntries) {
                    log.info("Following logs were found during execution test {} {}", methodName, entry.getMessage());
                }
            }
        } else {
            log.info("Drives is 'null' cant perform analyze logs");
        }
    }
}
